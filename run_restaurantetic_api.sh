docker run -it\
	--name restaurantetic-api\
	-p 9999:8080\
	-v $HOME/Developments/restaurantetic-api:/app\
	-d joseluis8906/openjdk:8u181-jdk-stretch
