package com.branchapp.encryption.impl;

import android.util.Base64;
import com.branchapp.encryption.api.EncryptRequestHeaderValue;
import java.nio.charset.Charset;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Singleton;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import timber.log.Timber;

/* compiled from: EncryptRequestHeaderValueImpl.kt */
@Singleton
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0096\u0002¨\u0006\b"}, d2 = {"Lcom/branchapp/encryption/impl/EncryptRequestHeaderValueImpl;", "Lcom/branchapp/encryption/api/EncryptRequestHeaderValue;", "()V", "invoke", "", "key", "str", "Companion", "impl_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class EncryptRequestHeaderValueImpl implements EncryptRequestHeaderValue {
    private static final String CHARSET_NAME = "UTF-8";
    public static final Companion Companion = new Companion(null);
    private static final String TRANSFORMATION_PATTERN = "AES/CTR/NoPadding";

    /* compiled from: EncryptRequestHeaderValueImpl.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/branchapp/encryption/impl/EncryptRequestHeaderValueImpl$Companion;", "", "()V", "CHARSET_NAME", "", "TRANSFORMATION_PATTERN", "impl_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* loaded from: classes13.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    @Override // com.branchapp.encryption.api.EncryptRequestHeaderValue
    public String invoke(String key, String str) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(str, "str");
        Cipher cipher = Cipher.getInstance(TRANSFORMATION_PATTERN);
        try {
            byte[] bytes = key.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
            cipher.init(1, new SecretKeySpec(bytes, "AES"));
            Charset forName = Charset.forName(CHARSET_NAME);
            Intrinsics.checkNotNullExpressionValue(forName, "forName(...)");
            byte[] bytes2 = str.getBytes(forName);
            Intrinsics.checkNotNullExpressionValue(bytes2, "getBytes(...)");
            byte[] doFinal = cipher.doFinal(bytes2);
            byte[] iv = cipher.getIV();
            Intrinsics.checkNotNullExpressionValue(iv, "getIV(...)");
            Intrinsics.checkNotNull(doFinal);
            String encodeToString = Base64.encodeToString(ArraysKt.plus(iv, doFinal), 2);
            Intrinsics.checkNotNullExpressionValue(encodeToString, "encodeToString(...)");
            return encodeToString;
        } catch (NullPointerException e) {
            Timber.Forest.e(e);
            return "";
        }
    }
}
