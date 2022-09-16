package com.aboitizpower.app;

import static com.aboitizpower.app.util.Config.getProperty;

import com.aboitizpower.app.instance.JenkinsServer;
import com.hashicorp.cdktf.TerraformOutput;
import com.hashicorp.cdktf.TerraformStack;
import com.hashicorp.cdktf.providers.aws.AwsProvider;
import com.hashicorp.cdktf.providers.aws.ec2.Instance;
import software.constructs.Construct;

public class MainStack extends TerraformStack
{
    private static final String REGION = "region";
    public static final String AWS = "AWS";
    public static final String JENKINS_SERVER_IP = "jenkins_server_ip";

    public MainStack(final Construct scope, final String id) {
        super(scope, id);

        AwsProvider.Builder.create(this, AWS)
                .region(getProperty(REGION))
                .build();

        Instance jenkinsInstance = new JenkinsServer(this);

        TerraformOutput.Builder.create(this, JENKINS_SERVER_IP)
                .value(jenkinsInstance.getPublicIp())
                .build();
    }
}