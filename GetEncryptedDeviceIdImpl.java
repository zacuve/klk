package com.branchapp.encryption.impl;

import android.content.Context;
import com.branchapp.encryption.api.EncryptRequestHeaderValue;
import com.branchapp.encryption.api.GetEncryptedDeviceId;
import com.branchmessenger.persistence.legacy.prefs.PreferencesProvider;
import com.google.android.gms.appset.AppSet;
import com.google.android.gms.appset.AppSetIdInfo;
import com.google.android.gms.tasks.Tasks;
import dagger.hilt.android.qualifiers.ApplicationContext;
import java.util.UUID;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import timber.log.Timber;

/* compiled from: GetEncryptedDeviceIdImpl.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B!\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0010\u001a\u00020\nH\u0002J\t\u0010\u0011\u001a\u00020\nH\u0096\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/branchapp/encryption/impl/GetEncryptedDeviceIdImpl;", "Lcom/branchapp/encryption/api/GetEncryptedDeviceId;", "context", "Landroid/content/Context;", "prefs", "Lcom/branchmessenger/persistence/legacy/prefs/PreferencesProvider;", "encryptHeaderValue", "Lcom/branchapp/encryption/api/EncryptRequestHeaderValue;", "(Landroid/content/Context;Lcom/branchmessenger/persistence/legacy/prefs/PreferencesProvider;Lcom/branchapp/encryption/api/EncryptRequestHeaderValue;)V", "deviceId", "", "getDeviceId", "()Ljava/lang/String;", "deviceId$delegate", "Lkotlin/Lazy;", "encryptedDeviceId", "getEncryptedDeviceId", "invoke", "Companion", "impl_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class GetEncryptedDeviceIdImpl implements GetEncryptedDeviceId {
    public static final Companion Companion = new Companion(null);
    private static final String DEVICE_ID_ERROR = "Failed to use AppSet and will default to generated UUID";
    private final Context context;
    private final Lazy deviceId$delegate;
    private final EncryptRequestHeaderValue encryptHeaderValue;
    private String encryptedDeviceId;
    private final PreferencesProvider prefs;

    @Inject
    public GetEncryptedDeviceIdImpl(@ApplicationContext Context context, PreferencesProvider prefs, EncryptRequestHeaderValue encryptHeaderValue) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(prefs, "prefs");
        Intrinsics.checkNotNullParameter(encryptHeaderValue, "encryptHeaderValue");
        this.context = context;
        this.prefs = prefs;
        this.encryptHeaderValue = encryptHeaderValue;
        this.deviceId$delegate = LazyKt.lazy(new Function0<String>() { // from class: com.branchapp.encryption.impl.GetEncryptedDeviceIdImpl$deviceId$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                PreferencesProvider preferencesProvider;
                PreferencesProvider preferencesProvider2;
                Context context2;
                preferencesProvider = GetEncryptedDeviceIdImpl.this.prefs;
                String deviceId = preferencesProvider.getHelper().getDeviceId();
                GetEncryptedDeviceIdImpl getEncryptedDeviceIdImpl = GetEncryptedDeviceIdImpl.this;
                if (StringsKt.isBlank(deviceId)) {
                    try {
                        context2 = getEncryptedDeviceIdImpl.context;
                        deviceId = ((AppSetIdInfo) Tasks.await(AppSet.getClient(context2).getAppSetIdInfo())).getId();
                    } catch (Exception e) {
                        Timber.Forest.e(e, "Failed to use AppSet and will default to generated UUID", new Object[0]);
                        deviceId = UUID.randomUUID().toString();
                    }
                    Intrinsics.checkNotNull(deviceId);
                    preferencesProvider2 = getEncryptedDeviceIdImpl.prefs;
                    preferencesProvider2.getHelper().setDeviceId(deviceId);
                }
                return deviceId;
            }
        });
    }

    private final String getDeviceId() {
        return (String) this.deviceId$delegate.getValue();
    }

    @Override // com.branchapp.encryption.api.GetEncryptedDeviceId
    public String invoke() {
        String str = this.encryptedDeviceId;
        if (str == null) {
            String encryptedDeviceId = getEncryptedDeviceId();
            this.encryptedDeviceId = encryptedDeviceId;
            return encryptedDeviceId;
        }
        return str;
    }

    private final String getEncryptedDeviceId() {
        return this.encryptHeaderValue.invoke(BuildConfig.HEK, getDeviceId());
    }

    /* compiled from: GetEncryptedDeviceIdImpl.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/branchapp/encryption/impl/GetEncryptedDeviceIdImpl$Companion;", "", "()V", "DEVICE_ID_ERROR", "", "impl_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* loaded from: classes13.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
