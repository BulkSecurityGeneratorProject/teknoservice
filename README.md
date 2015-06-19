README for teknoservice
==========================

mvn -Dmaven.test.skip=true -Pcloud package


cf push -f ./deploy/cloudfoundry/manifest.yml -p target/teknoservice-0.0.1-SNAPSHOT.war


mvn liquibase:diff

mvn liquibase:generateChangeLog

# HEROKU
C:\Users\utente\git\teknoservice>heroku help
Usage: heroku COMMAND [--app APP] [command-specific-options]

Primary help topics, type "heroku help TOPIC" for more details:

  addons    #  manage addon resources
  apps      #  manage apps (create, destroy)
  auth      #  authentication (login, logout)
  config    #  manage app config vars
  domains   #  manage custom domains
  logs      #  display logs for an app
  ps        #  manage dynos (dynos, workers)
  releases  #  manage app releases
  run       #  run one-off commands (console, rake)
  sharing   #  manage collaborators on an app

Additional topics:

  buildpacks   #  manage the buildpack for an app
  certs        #  manage ssl endpoints for an app
  deploy       #  deploy to an app
  drains       #  display drains for an app
  features     #  manage optional features
  fork         #  clone an existing app
  git          #  manage git for apps
  help         #  list commands and display help
  keys         #  manage authentication keys
  labs         #  manage optional features
  local        #  run heroku app locally
  maintenance  #  manage maintenance mode for an app
  members      #  manage membership in organization accounts
  orgs         #  manage organization accounts
  pg           #  manage heroku-postgresql databases
  pgbackups    #  manage backups of heroku postgresql databases
  plugins      #  manage plugins to the heroku gem
  redis        #  list redis databases for an app
  regions      #  list available regions
  stack        #  manage the stack for an app
  status       #  check status of heroku platform
  twofactor    #  manage two-factor authentication settings
  update       #  update the heroku client
  version      #  display version

https://devcenter.heroku.com/articles/deploying-java-applications-with-the-heroku-maven-plugin

create app: https://devcenter.heroku.com/articles/creating-apps

> heroku create teknoservice

> mvn clean heroku:deploy-war -P prod -DskipTests=true

<configuration>
<processTypes>
<web>java -Xmx384m -Xss512k -XX:+UseCompressedOops -jar target/*.war --spring.profiles.active=prod --server.port=$PORT --spring.datasource.heroku-url=$DATABASE_URL</web>
</processTypes>
</configuration>

heroku buildpacks:set https://github.com/heroku/heroku-buildpack-java

$ git remote -v 
|| $ git remove rm heroku ? se obsoleto
$ heroku create teknoservice
$ heroku buildpacks:add https://github.com/heroku/heroku-buildpack-nodejs
$ heroku buildpacks:add https://github.com/heroku/heroku-buildpack-java
$ heroku config:set MAVEN_CUSTOM_OPTS="-Pprod -DskipTests=true"
$ git push heroku master

scale> heroku ps:scale web=1

heroku logs -tail --app teknoservice

heroku apps:create teknoservice --region eu && heroku config:set NODE_ENV=production --app teknoservice