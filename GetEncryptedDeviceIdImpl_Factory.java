package com.branchapp.encryption.impl;

import android.content.Context;
import com.branchapp.encryption.api.EncryptRequestHeaderValue;
import com.branchmessenger.persistence.legacy.prefs.PreferencesProvider;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes13.dex */
public final class GetEncryptedDeviceIdImpl_Factory implements Factory<GetEncryptedDeviceIdImpl> {
    private final Provider<Context> contextProvider;
    private final Provider<EncryptRequestHeaderValue> encryptHeaderValueProvider;
    private final Provider<PreferencesProvider> prefsProvider;

    public GetEncryptedDeviceIdImpl_Factory(Provider<Context> contextProvider, Provider<PreferencesProvider> prefsProvider, Provider<EncryptRequestHeaderValue> encryptHeaderValueProvider) {
        this.contextProvider = contextProvider;
        this.prefsProvider = prefsProvider;
        this.encryptHeaderValueProvider = encryptHeaderValueProvider;
    }

    @Override // javax.inject.Provider
    public GetEncryptedDeviceIdImpl get() {
        return newInstance(this.contextProvider.get(), this.prefsProvider.get(), this.encryptHeaderValueProvider.get());
    }

    public static GetEncryptedDeviceIdImpl_Factory create(Provider<Context> contextProvider, Provider<PreferencesProvider> prefsProvider, Provider<EncryptRequestHeaderValue> encryptHeaderValueProvider) {
        return new GetEncryptedDeviceIdImpl_Factory(contextProvider, prefsProvider, encryptHeaderValueProvider);
    }

    public static GetEncryptedDeviceIdImpl newInstance(Context context, PreferencesProvider prefs, EncryptRequestHeaderValue encryptHeaderValue) {
        return new GetEncryptedDeviceIdImpl(context, prefs, encryptHeaderValue);
    }
}
