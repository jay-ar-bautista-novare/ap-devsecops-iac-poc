package com.aboitizpower.app.instance;

import com.hashicorp.cdktf.TerraformVariable;
import software.constructs.Construct;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.aboitizpower.app.util.Config.getProperty;
import static com.aboitizpower.app.util.FileHelper.retrieveFileContent;
import static com.aboitizpower.app.util.FileHelper.BIN_BASH;
import static com.aboitizpower.app.util.FileHelper.NEW_LINE;
import static com.aboitizpower.app.util.FileHelper.INSTALL_DOCKER_PATH;

public final class SonarQubeServerInstance extends Ec2Instance {

    private static final String SONARQUBE_SERVER_NAME = "sonarqube.server.name";
    private static final String SONARQUBE_SERVER_INSTANCE_TYPE = "sonarqube.server.instance.type";
    private static final String SONARQUBE_SERVER_SECURITY_GROUPS = "sonarqube.server.security.groups";
    private static final String SONARQUBE_SERVER_SUBNET_ID = "sonarqube.server.subnet.id";

    public SonarQubeServerInstance(Construct scope, TerraformVariable environment) {
        super(scope, getProperty(SONARQUBE_SERVER_NAME));
        setTags(getNameTag(environment.getStringValue(), getProperty(SONARQUBE_SERVER_NAME)));
        setAmi(getProperty(SERVER_AMI));
        setInstanceType(getProperty(SONARQUBE_SERVER_INSTANCE_TYPE));
        setSubnetId(getProperty(SONARQUBE_SERVER_SUBNET_ID));
        setUserData(getInstallationScript());
        addSecurityGroups(SONARQUBE_SERVER_SECURITY_GROUPS);
    }

    @Override
    protected String getInstallationScript() {
        Path installDockerScript = Paths.get(INSTALL_DOCKER_PATH);

        return BIN_BASH + NEW_LINE +
                retrieveFileContent(installDockerScript);
    }
}
