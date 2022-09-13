package com.aboitizpower.app;

import com.hashicorp.cdktf.TerraformOutput;
import com.hashicorp.cdktf.TerraformStack;
import com.hashicorp.cdktf.TerraformVariable;
import com.hashicorp.cdktf.providers.aws.AwsProvider;
import com.hashicorp.cdktf.providers.aws.ec2.Instance;
import software.constructs.Construct;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainStack extends TerraformStack
{
    public MainStack(final Construct scope, final String id) {
        super(scope, id);

        AwsProvider.Builder.create(this, "AWS")
                .region("ap-southeast-1")
                .build();

        TerraformVariable environment = TerraformVariable.Builder.create(this, "environment")
                .type(String.class.getSimpleName().toLowerCase())
                .build();

        Map<String, String> tags = new HashMap<>();
        tags.put("Name", environment.getStringValue() + "-JenkinsServer");

        Instance instance = Instance.Builder.create(this, "compute")
                .tags(tags)
                .ami("ami-0b89f7b3f054b957e")
                .instanceType("t3.micro")
                .securityGroups(Collections.singletonList("sg-0541d523b2625fa85"))
                .subnetId("subnet-07225cb9aa01bbb0f")
                .build();

        TerraformOutput.Builder.create(this, "public_ip")
                .value(instance.getPublicIp())
                .build();
    }
}