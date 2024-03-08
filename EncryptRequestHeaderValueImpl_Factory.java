package com.branchapp.encryption.impl;

import dagger.internal.Factory;

/* loaded from: classes13.dex */
public final class EncryptRequestHeaderValueImpl_Factory implements Factory<EncryptRequestHeaderValueImpl> {
    @Override // javax.inject.Provider
    public EncryptRequestHeaderValueImpl get() {
        return newInstance();
    }

    public static EncryptRequestHeaderValueImpl_Factory create() {
        return InstanceHolder.INSTANCE;
    }

    public static EncryptRequestHeaderValueImpl newInstance() {
        return new EncryptRequestHeaderValueImpl();
    }

    /* loaded from: classes13.dex */
    private static final class InstanceHolder {
        private static final EncryptRequestHeaderValueImpl_Factory INSTANCE = new EncryptRequestHeaderValueImpl_Factory();

        private InstanceHolder() {
        }
    }
}
