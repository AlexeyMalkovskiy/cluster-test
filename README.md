# Build
mvn clean package && docker build -t com.airhacks/cluster-test .

# Deploy
1. ./jboss-cli.sh --connect --command="deploy /home/alexey/work/workspace-edu/wildfly/cluster-test-service/cluster-test/target/cluster-test.war --server-groups=backend-servers"
1. ./jboss-cli.sh --connect --command="deploy /home/alexey/work/workspace-edu/wildfly/cluster-test-service/cluster-test/target/cluster-test.war --force"


# RUN

docker rm -f cluster-test || true && docker run -d -p 8080:8080 -p 4848:4848 --name cluster-test com.airhacks/cluster-test 

# System Test

Switch to the "-st" module and perform:

mvn compile failsafe:integration-test