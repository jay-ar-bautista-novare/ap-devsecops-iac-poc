package com.aboitizpower.app;

import static com.aboitizpower.app.util.Config.getProperty;

import com.aboitizpower.app.instance.JenkinsServerInstance;
import com.aboitizpower.app.instance.SonarQubeServerInstance;
import com.hashicorp.cdktf.TerraformOutput;
import com.hashicorp.cdktf.TerraformStack;
import com.hashicorp.cdktf.TerraformVariable;
import com.hashicorp.cdktf.providers.aws.AwsProvider;
import com.hashicorp.cdktf.providers.aws.ec2.Instance;
import software.constructs.Construct;

public class MainStack extends TerraformStack
{
    private static final String REGION = "region";
    private static final String AWS = "AWS";
    private static final String JENKINS_SERVER_IP = "jenkins_server_ip";
    private static final String SONARQUBE_SERVER_IP = "sonarqube_server_ip";
    private static final String ENVIRONMENT = "environment";

    public MainStack(final Construct scope, final String id) {
        super(scope, id);

        TerraformVariable environment = TerraformVariable.Builder.create(this, ENVIRONMENT)
                .type(String.class.getSimpleName().toLowerCase())
                .build();

        AwsProvider.Builder.create(this, AWS)
                .region(getProperty(REGION))
                .build();

        Instance jenkinsInstance = new JenkinsServerInstance(this, environment);
        Instance sonarqubeInstance = new SonarQubeServerInstance(this, environment);

        TerraformOutput.Builder.create(this, JENKINS_SERVER_IP)
                .value(jenkinsInstance.getPublicIp())
                .build();
        TerraformOutput.Builder.create(this, SONARQUBE_SERVER_IP)
                .value(sonarqubeInstance.getPublicIp())
                .build();
    }
}