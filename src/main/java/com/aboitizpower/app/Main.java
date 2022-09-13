package com.aboitizpower.app;

import com.hashicorp.cdktf.App;
import com.hashicorp.cdktf.NamedCloudWorkspace;
import com.hashicorp.cdktf.CloudBackend;
import com.hashicorp.cdktf.CloudBackendProps;


public class Main
{
    public static void main(String[] args) {
        final App app = new App();
        MainStack stack = new MainStack(app, "ap-devsecops-iac-poc");
        new CloudBackend(stack, CloudBackendProps.builder().hostname("app.terraform.io").organization("mdixapc").workspaces(new NamedCloudWorkspace("ap-devsecops-iac-poc")).build());
        app.synth();
    }
}