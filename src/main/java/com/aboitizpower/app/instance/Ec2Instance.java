package com.aboitizpower.app.instance;

import com.hashicorp.cdktf.TerraformVariable;
import com.hashicorp.cdktf.providers.aws.ec2.Instance;
import software.constructs.Construct;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.aboitizpower.app.util.Config.getProperty;
public abstract class Ec2Instance extends Instance {

    public static final String SERVER_AMI = "server.ami";
    public static final String DELIMITER = "\\s*,\\s*";
    private static final String ENVIRONMENT = "environment";
    private static final String HYPHEN = "-";
    private static final String NAME_KEY = "Name";

    protected Ec2Instance(Construct scope, String id) {
        super(scope, id);
    }

    protected void addSecurityGroups(String securityGroupKey) {
        String securityGroupValue = getProperty(securityGroupKey);
        List<String> items = Arrays.asList(securityGroupValue.split(DELIMITER));
        setSecurityGroups(items);
    }
    
    protected Map<String, String> getNameTag(Construct scope, String serverName) {
        TerraformVariable environment = TerraformVariable.Builder.create(scope, ENVIRONMENT)
                .type(String.class.getSimpleName().toLowerCase())
                .build();

        return Collections.singletonMap(NAME_KEY, environment.getStringValue() + HYPHEN + serverName);
    }

    protected abstract String getInstallationScript();
}
