sudo yum update -y
sudo yum search docker
sudo yum info docker
sudo yum install docker -y
sudo service docker start
sudo usermod -a -G docker ec2-user
id ec2-user
