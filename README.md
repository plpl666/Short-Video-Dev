# Short-Video
该项目是一款社交类短视频小程序,用户可以在平台分享自己的短视频，或者观看其他用户的短视频，可对视频进行点赞评论收藏举报等操作。 
1，采用stringboot作为项目整体框架，简化开发。 
2，采用zookeeper来实现分布式服务器协调服务。 
3，采用ffmpeg对音、视频进行处理。 
4，采用redis缓存用户登录信息，构建无状态session，过滤用户登录。 
5，使用mysql对信息数据进行存储。