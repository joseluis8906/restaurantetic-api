docker run -it\
	--name almundo-api\
	-p 3000:3000\
	-p 9229:9229\
	-v $HOME/Developments/almundo-api:/app\
	-d node:10.15.3-stretch
