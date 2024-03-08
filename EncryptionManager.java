package com.branchapp.encryption.api;

import kotlin.Metadata;

/* compiled from: EncryptionManager.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H&J\b\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, d2 = {"Lcom/branchapp/encryption/api/EncryptionManager;", "", "decryptData", "", "keyAlias", "encryptedData", "deleteAllData", "", "encryptData", "data", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes12.dex */
public interface EncryptionManager {
    String decryptData(String str, String str2);

    void deleteAllData();

    String encryptData(String str, String str2);
}
