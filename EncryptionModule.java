package com.branchapp.encryption.glue;

import com.branchapp.encryption.api.EncryptRequestHeaderValue;
import com.branchapp.encryption.api.EncryptionManager;
import com.branchapp.encryption.api.GetEncryptedDeviceId;
import com.branchapp.encryption.api.GetEncryptedUserAgent;
import com.branchapp.encryption.impl.EncryptRequestHeaderValueImpl;
import com.branchapp.encryption.impl.EncryptionManagerImpl;
import com.branchapp.encryption.impl.GetEncryptedDeviceIdImpl;
import com.branchapp.encryption.impl.GetEncryptedUserAgentImpl;
import dagger.Binds;
import dagger.Module;
import javax.inject.Singleton;
import kotlin.Metadata;

/* compiled from: EncryptionModule.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\ba\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH'J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH'J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H'¨\u0006\u0012"}, d2 = {"Lcom/branchapp/encryption/glue/EncryptionModule;", "", "bindsEncryptRequestHeaderValue", "Lcom/branchapp/encryption/api/EncryptRequestHeaderValue;", "encryptRequestHeaderValue", "Lcom/branchapp/encryption/impl/EncryptRequestHeaderValueImpl;", "bindsEncryptionManager", "Lcom/branchapp/encryption/api/EncryptionManager;", "encryptionManager", "Lcom/branchapp/encryption/impl/EncryptionManagerImpl;", "bindsGetEncryptedDeviceId", "Lcom/branchapp/encryption/api/GetEncryptedDeviceId;", "getEncryptedDeviceId", "Lcom/branchapp/encryption/impl/GetEncryptedDeviceIdImpl;", "bindsGetEncryptedUserAgent", "Lcom/branchapp/encryption/api/GetEncryptedUserAgent;", "getEncryptedUserAgent", "Lcom/branchapp/encryption/impl/GetEncryptedUserAgentImpl;", "glue_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Module
/* loaded from: classes13.dex */
public interface EncryptionModule {
    @Singleton
    @Binds
    EncryptRequestHeaderValue bindsEncryptRequestHeaderValue(EncryptRequestHeaderValueImpl encryptRequestHeaderValueImpl);

    @Singleton
    @Binds
    EncryptionManager bindsEncryptionManager(EncryptionManagerImpl encryptionManagerImpl);

    @Singleton
    @Binds
    GetEncryptedDeviceId bindsGetEncryptedDeviceId(GetEncryptedDeviceIdImpl getEncryptedDeviceIdImpl);

    @Singleton
    @Binds
    GetEncryptedUserAgent bindsGetEncryptedUserAgent(GetEncryptedUserAgentImpl getEncryptedUserAgentImpl);
}
