package com.branchapp.encryption.impl;

import android.security.keystore.KeyGenParameterSpec;
import android.util.Base64;
import com.branchapp.encryption.api.EncryptionManager;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Regex;
import timber.log.Timber;

/* compiled from: EncryptionManagerImpl.kt */
@Singleton
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u0004H\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002J\u0010\u0010\r\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¨\u0006\u000f"}, d2 = {"Lcom/branchapp/encryption/impl/EncryptionManagerImpl;", "Lcom/branchapp/encryption/api/EncryptionManager;", "()V", "decryptData", "", "keyAlias", "encryptedData", "deleteAllData", "", "encryptData", "data", "generateSecretKey", "Ljavax/crypto/SecretKey;", "getSecretKey", "Companion", "impl_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class EncryptionManagerImpl implements EncryptionManager {
    private static final String CHARSET_NAME = "UTF-8";
    public static final Companion Companion = new Companion(null);
    private static final String IV_ERROR_MESSAGE = "Passed data is incorrect. There was no IV specified with it.";
    private static final String IV_SEPARATOR = "]";
    private static final String KEY_STORE_TYPE = "AndroidKeyStore";
    private static final String TRANSFORMATION_PATTERN = "AES/CBC/PKCS7Padding";

    @Override // com.branchapp.encryption.api.EncryptionManager
    public String encryptData(String keyAlias, String data) {
        Intrinsics.checkNotNullParameter(keyAlias, "keyAlias");
        Intrinsics.checkNotNullParameter(data, "data");
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION_PATTERN);
            cipher.init(1, generateSecretKey(keyAlias));
            String str = Base64.encodeToString(cipher.getIV(), 2) + IV_SEPARATOR;
            Charset forName = Charset.forName(CHARSET_NAME);
            Intrinsics.checkNotNullExpressionValue(forName, "forName(...)");
            byte[] bytes = data.getBytes(forName);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            return str + Base64.encodeToString(cipher.doFinal(bytes), 2);
        } catch (Exception e) {
            Timber.Forest.e(e);
            return "";
        }
    }

    @Override // com.branchapp.encryption.api.EncryptionManager
    public String decryptData(String keyAlias, String encryptedData) {
        Intrinsics.checkNotNullParameter(keyAlias, "keyAlias");
        Intrinsics.checkNotNullParameter(encryptedData, "encryptedData");
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION_PATTERN);
            List<String> split = new Regex(IV_SEPARATOR).split(encryptedData, 0);
            if (split.size() != 2) {
                throw new IllegalArgumentException(IV_ERROR_MESSAGE);
            }
            cipher.init(2, getSecretKey(keyAlias), new IvParameterSpec(Base64.decode(split.get(0), 2)));
            byte[] doFinal = cipher.doFinal(Base64.decode(split.get(1), 2));
            Intrinsics.checkNotNull(doFinal);
            return new String(doFinal, Charsets.UTF_8);
        } catch (Exception e) {
            Timber.Forest.e(e);
            return "";
        }
    }

    @Override // com.branchapp.encryption.api.EncryptionManager
    public void deleteAllData() {
        KeyStore keyStore = KeyStore.getInstance(KEY_STORE_TYPE);
        keyStore.load(null);
        Enumeration<String> aliases = keyStore.aliases();
        Intrinsics.checkNotNullExpressionValue(aliases, "aliases(...)");
        Iterator it = CollectionsKt.iterator(aliases);
        while (it.hasNext()) {
            try {
                keyStore.deleteEntry((String) it.next());
            } catch (Exception e) {
                Timber.Forest.e(e);
            }
        }
    }

    private final SecretKey generateSecretKey(String str) {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", KEY_STORE_TYPE);
        keyGenerator.init(new KeyGenParameterSpec.Builder(str, 3).setBlockModes("CBC").setEncryptionPaddings("PKCS7Padding").build());
        SecretKey generateKey = keyGenerator.generateKey();
        Intrinsics.checkNotNullExpressionValue(generateKey, "generateKey(...)");
        return generateKey;
    }

    private final SecretKey getSecretKey(String str) {
        KeyStore keyStore = KeyStore.getInstance(KEY_STORE_TYPE);
        keyStore.load(null);
        KeyStore.Entry entry = keyStore.getEntry(str, null);
        Intrinsics.checkNotNull(entry, "null cannot be cast to non-null type java.security.KeyStore.SecretKeyEntry");
        SecretKey secretKey = ((KeyStore.SecretKeyEntry) entry).getSecretKey();
        Intrinsics.checkNotNullExpressionValue(secretKey, "getSecretKey(...)");
        return secretKey;
    }

    /* compiled from: EncryptionManagerImpl.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/branchapp/encryption/impl/EncryptionManagerImpl$Companion;", "", "()V", "CHARSET_NAME", "", "IV_ERROR_MESSAGE", "IV_SEPARATOR", "KEY_STORE_TYPE", "TRANSFORMATION_PATTERN", "impl_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* loaded from: classes13.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
