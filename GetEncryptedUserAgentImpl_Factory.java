package com.branchapp.encryption.impl;

import android.content.Context;
import com.branchapp.buildconfig.api.BranchBuildConfig;
import com.branchapp.encryption.api.EncryptRequestHeaderValue;
import dagger.internal.Factory;
import javax.inject.Provider;

/* loaded from: classes13.dex */
public final class GetEncryptedUserAgentImpl_Factory implements Factory<GetEncryptedUserAgentImpl> {
    private final Provider<BranchBuildConfig> branchBuildConfigProvider;
    private final Provider<Context> contextProvider;
    private final Provider<EncryptRequestHeaderValue> encryptHeaderValueProvider;

    public GetEncryptedUserAgentImpl_Factory(Provider<Context> contextProvider, Provider<BranchBuildConfig> branchBuildConfigProvider, Provider<EncryptRequestHeaderValue> encryptHeaderValueProvider) {
        this.contextProvider = contextProvider;
        this.branchBuildConfigProvider = branchBuildConfigProvider;
        this.encryptHeaderValueProvider = encryptHeaderValueProvider;
    }

    @Override // javax.inject.Provider
    public GetEncryptedUserAgentImpl get() {
        return newInstance(this.contextProvider.get(), this.branchBuildConfigProvider.get(), this.encryptHeaderValueProvider.get());
    }

    public static GetEncryptedUserAgentImpl_Factory create(Provider<Context> contextProvider, Provider<BranchBuildConfig> branchBuildConfigProvider, Provider<EncryptRequestHeaderValue> encryptHeaderValueProvider) {
        return new GetEncryptedUserAgentImpl_Factory(contextProvider, branchBuildConfigProvider, encryptHeaderValueProvider);
    }

    public static GetEncryptedUserAgentImpl newInstance(Context context, BranchBuildConfig branchBuildConfig, EncryptRequestHeaderValue encryptHeaderValue) {
        return new GetEncryptedUserAgentImpl(context, branchBuildConfig, encryptHeaderValue);
    }
}
