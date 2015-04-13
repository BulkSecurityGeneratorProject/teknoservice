README for teknoservice
==========================

mvn -Dmaven.test.skip=true -Pcloud package


cf push -f ./deploy/cloudfoundry/manifest.yml -p target/teknoservice-0.0.1-SNAPSHOT.war