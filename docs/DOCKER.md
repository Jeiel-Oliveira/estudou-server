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

Command to stop all containers:

docker stop $(docker ps -a -q)

#### jib:build

Before running the command below, is necessary to login in docker hub to push the images to the centralized server. Just follow the steps below:

- Login to docker
- provide username and password
- all related password and authentication informations are saved in .docker/config.json
- open the current project with the jib library installed and run the build command

Commands:

docker login
mvn clean compile jib:build

#### Mysql with docker

https://hevodata.com/learn/docker-mysql/

#### Basics of docker

Simple guide:
https://www.youtube.com/watch?v=pTFZFxd4hOI (Current in to understand the docker concepts better)

The main problem docker is trying to resolve, is the fact the application need to consistenly run in every enverioment. For example, if you are running an application in your local computer and the application run smoothly, but when you deploy brokes everything. The reason why can be:

- Some file is missing who is necessary to the application run
- The machine who is running your application in production has a different versions for any software you are using
- Some new employee in the company, your the friends of yours joins the coding, and she or he needs to install locally everything to start running and coding

##### Virtual machine vs Container

###### Virtual machine

- Hypervisor is used to run VMs
- A VM is a abstract of a real hardware in a software. For example, a mac can run a windows, and a linux at the same time using Hypervisors
- The VM needs an entire OS running tho run
- They are heavy
- All the OS utilized needs to be monitored and licensed


###### Container

- The Containers share the kernel with the rost
- The container are lightweigth
- A container has his own filesystem

##### Docker archictecture

Docker Client -- Via API --> Docker Engine (Server)

- A container is a special type of process running in your machine

#### Development work flow

- Gets application
- Docker file is a file to package a application in to a image
- We need to tell docker to run the image in a container'
- After we test locally your application, we can upload your image to a registry (like Docker hub)
- Docker Hub is like github to git

DEVELOPMENT -- Upload image to registry --> REGISTRY -- Download image to production ENV --> TEST / PROD

In the example of a simple javascript program who just prints hello world via console.log.
If we need to run the application in other device who is not configured yet, we nedd:

- An OS
- A node enviroment
- Copy the code
- Execute the code with node
- Docker is built on basic linux concepts

Official images are offialy distribuited in hub.docker.com

apt - advanced package tools
pwd - print work directory

Guide to run docker compose locally:
https://medium.com/simform-engineering/setting-up-a-local-development-environment-using-docker-compose-551efb4ec0ee

#### Instructions for hosts keycloak locally

To run keycloak locally is necessary follo this steps

- cd /etc
- Open the file hosts and add the text "127.0.0.1   keycloak". This will map the keycloak hosts and vinculate to localhost hosts
- Create a database to keycloak - mysql with the name keycloak

GUIDE TO DOCKER

https://www.youtube.com/watch?v=zkMRWDQV4Tg

Complete docker guide:
https://www.youtube.com/watch?v=fqMOX6JJhGo
