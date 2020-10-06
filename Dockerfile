FROM airhacks/glassfish
COPY ./target/cluster-test.war ${DEPLOYMENT_DIR}
