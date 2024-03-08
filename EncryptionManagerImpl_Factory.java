package com.branchapp.encryption.impl;

import dagger.internal.Factory;

/* loaded from: classes13.dex */
public final class EncryptionManagerImpl_Factory implements Factory<EncryptionManagerImpl> {
    @Override // javax.inject.Provider
    public EncryptionManagerImpl get() {
        return newInstance();
    }

    public static EncryptionManagerImpl_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static EncryptionManagerImpl newInstance() {
        return new EncryptionManagerImpl();
    }

    /* loaded from: classes13.dex */
    private static final class InstanceHolder {
        private static final EncryptionManagerImpl_Factory INSTANCE = new EncryptionManagerImpl_Factory();

        private InstanceHolder() {
        }
    }
}
