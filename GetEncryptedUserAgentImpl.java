package com.branchapp.encryption.impl;

import android.content.Context;
import android.os.Build;
import com.branchapp.buildconfig.api.BranchBuildConfig;
import com.branchapp.encryption.api.EncryptRequestHeaderValue;
import com.branchapp.encryption.api.GetEncryptedUserAgent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: GetEncryptedUserAgentImpl.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0010\u001a\u00020\nH\u0002J\b\u0010\u0011\u001a\u00020\nH\u0002J\t\u0010\u0012\u001a\u00020\nH\u0096\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lcom/branchapp/encryption/impl/GetEncryptedUserAgentImpl;", "Lcom/branchapp/encryption/api/GetEncryptedUserAgent;", "context", "Landroid/content/Context;", "branchBuildConfig", "Lcom/branchapp/buildconfig/api/BranchBuildConfig;", "encryptHeaderValue", "Lcom/branchapp/encryption/api/EncryptRequestHeaderValue;", "(Landroid/content/Context;Lcom/branchapp/buildconfig/api/BranchBuildConfig;Lcom/branchapp/encryption/api/EncryptRequestHeaderValue;)V", "encryptedUserAgent", "", "userAgent", "getUserAgent", "()Ljava/lang/String;", "userAgent$delegate", "Lkotlin/Lazy;", "getDeviceName", "getEncryptedUserAgent", "invoke", "impl_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class GetEncryptedUserAgentImpl implements GetEncryptedUserAgent {
    private final BranchBuildConfig branchBuildConfig;
    private final Context context;
    private final EncryptRequestHeaderValue encryptHeaderValue;
    private String encryptedUserAgent;
    private final Lazy userAgent$delegate;

    @Inject
    public GetEncryptedUserAgentImpl(@ApplicationContext Context context, BranchBuildConfig branchBuildConfig, EncryptRequestHeaderValue encryptHeaderValue) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(branchBuildConfig, "branchBuildConfig");
        Intrinsics.checkNotNullParameter(encryptHeaderValue, "encryptHeaderValue");
        this.context = context;
        this.branchBuildConfig = branchBuildConfig;
        this.encryptHeaderValue = encryptHeaderValue;
        this.userAgent$delegate = LazyKt.lazy(new Function0<String>() { // from class: com.branchapp.encryption.impl.GetEncryptedUserAgentImpl$userAgent$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                BranchBuildConfig branchBuildConfig2;
                Context context2;
                String deviceName;
                branchBuildConfig2 = GetEncryptedUserAgentImpl.this.branchBuildConfig;
                String versionName = branchBuildConfig2.getVersionName();
                context2 = GetEncryptedUserAgentImpl.this.context;
                String packageName = context2.getPackageName();
                deviceName = GetEncryptedUserAgentImpl.this.getDeviceName();
                String str = Build.VERSION.RELEASE;
                return "Branch/" + versionName + " (" + packageName + "; " + deviceName + "; Android " + str + ")";
            }
        });
    }

    private final String getUserAgent() {
        return (String) this.userAgent$delegate.getValue();
    }

    @Override // com.branchapp.encryption.api.GetEncryptedUserAgent
    public String invoke() {
        String str = this.encryptedUserAgent;
        if (str == null) {
            String encryptedUserAgent = getEncryptedUserAgent();
            this.encryptedUserAgent = encryptedUserAgent;
            return encryptedUserAgent;
        }
        return str;
    }

    private final String getEncryptedUserAgent() {
        return this.encryptHeaderValue.invoke(BuildConfig.HEK, getUserAgent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getDeviceName() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        Intrinsics.checkNotNull(str2);
        Intrinsics.checkNotNull(str);
        if (StringsKt.startsWith$default(str2, str, false, 2, (Object) null)) {
            return com.branchapp.androidextensions.api.StringsKt.capitalizeWords(str2);
        }
        return com.branchapp.androidextensions.api.StringsKt.capitalizeWords(str + " " + str2);
    }
}
