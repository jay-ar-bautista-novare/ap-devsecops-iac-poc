sudo yum update -y
sudo yum search docker
sudo yum info docker
sudo yum install docker -y
sudo usermod -a -G docker ec2-user
id ec2-user
newgrp docker << 'END'
END
sudo systemctl enable docker.service
sudo systemctl start docker.service
