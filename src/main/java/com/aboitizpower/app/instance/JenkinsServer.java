package com.aboitizpower.app.instance;

import software.constructs.Construct;

import java.nio.file.Path;
import java.nio.file.Paths;

import static com.aboitizpower.app.util.Config.getProperty;
import static com.aboitizpower.app.util.FileHelper.retrieveFileContent;
import static com.aboitizpower.app.util.FileHelper.NEW_LINE;

public final class JenkinsServer extends Ec2Instance {

    private static final String JENKINS_SERVER_NAME = "jenkins.server.name";
    private static final String JENKINS_SERVER_INSTANCE_TYPE = "jenkins.server.instance.type";
    private static final String JENKINS_SERVER_SECURITY_GROUPS = "jenkins.server.security.groups";
    private static final String JENKINS_SERVER_SUBNET_ID = "jenkins.server.subnet.id";
    public static final String INSTALL_DOCKER_PATH = "src/main/sh/install_docker.sh";
    public static final String INSTALL_JENKINS_PATH = "src/main/sh/install_jenkins.sh";
    public static final String INSTALL_BLUE_OCEAN_PATH = "src/main/sh/install_jenkins_blue_ocean.sh";
    public static final String BIN_BASH = "#!/bin/bash";

    public JenkinsServer(Construct scope) {
        super(scope, getProperty(JENKINS_SERVER_NAME));
        setTags(getNameTag(scope, getProperty(JENKINS_SERVER_NAME)));
        setAmi(getProperty(SERVER_AMI));
        setInstanceType(getProperty(JENKINS_SERVER_INSTANCE_TYPE));
        setSubnetId(getProperty(JENKINS_SERVER_SUBNET_ID));
        setUserData(getInstallationScript());
        addSecurityGroups(JENKINS_SERVER_SECURITY_GROUPS);
    }

    @Override
    protected String getInstallationScript() {
        Path installDockerScript = Paths.get(INSTALL_DOCKER_PATH);
        Path installJenkinsScript = Paths.get(INSTALL_JENKINS_PATH);
        Path installJenkinsBlueOceanScript = Paths.get(INSTALL_BLUE_OCEAN_PATH);

        return BIN_BASH + NEW_LINE +
                retrieveFileContent(installDockerScript) +
                retrieveFileContent(installJenkinsScript) + NEW_LINE +
                retrieveFileContent(installJenkinsBlueOceanScript);
    }
}
