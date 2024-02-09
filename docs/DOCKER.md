### Docker

Permissions related problems when execute docker-compose and how to fix:
https://docs.docker.com/engine/install/linux-postinstall/#manage-docker-as-a-non-root-user

Configuration to push images to docker hub:
<to><image>registry.hub.docker.com/jeielalves/${project.artifactId}</image></to>

Unauthorized error in registry:
https://github.com/GoogleContainerTools/jib/blob/master/docs/faq.md#what-should-i-do-when-the-registry-responds-with-unauthorized

Authentication Method:
https://github.com/GoogleContainerTools/jib/blob/master/jib-maven-plugin/README.md#authentication-methods

Guide to authentication in docker files:
https://www.mankier.com/5/containers-auth.json

#### jib:build

Before running the command below, is necessary to login in docker hub to push the images to the centralized server. Just follow the steps below:

- Login to docker
- provide username and password
- all related password and authentication informations are saved in .docker/config.json
- open the current project with the jib library installed and run the build command

Commands:

docker login
mvn clean compile jib:build

#### Basics of docker

Simple guide:
https://www.youtube.com/watch?v=pTFZFxd4hOI (Current in to understand the docker concepts better)

The main problem docker is trying to resolve, is the fact the application need to consistenly run in every enverioment. For example, if you are running an application in your local computer and the application run smoothly, but when you deploy brokes everything. The reason why can be:

- Some file is missing who is necessary to the application run
- The machine who is running your application in production has a different versions for any software you are using
- Some new employee in the company, your the friends of yours joins the coding, and she or he needs to install locally everything to start running and coding
