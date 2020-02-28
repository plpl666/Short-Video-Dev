package com.imooc;

import com.imooc.config.ResourceConfig;
import com.imooc.enums.BgmOperatorType;
import com.imooc.utils.JsonUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;


@Component
public class ZKCuratorClient {

    private CuratorFramework client = null;
//    private final static Logger log = LoggerFactory.getLogger(ZKCuratorClient.class);

//    private static final String ZOOKEEPER_SERVER = "192.168.68.179:2181";

    @Autowired
    ResourceConfig resourceConfig;

    public void init() {
        if (client != null) {
            return;
        }
        //重连策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,5);
        //创建zk客户端
        client = CuratorFrameworkFactory.builder().connectString(resourceConfig.getZookeeperServer())
                .sessionTimeoutMs(10000).connectionTimeoutMs(10000)
                .retryPolicy(retryPolicy).namespace("admin").build();
        //启动客户端
        client.start();
        try {
            addChildWatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addChildWatch() throws Exception {
        final PathChildrenCache childrenCache = new PathChildrenCache(client, "/bgm",true);
        childrenCache.start();
        childrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                if (pathChildrenCacheEvent.getType().equals(PathChildrenCacheEvent.Type.CHILD_ADDED)) {
                    // 1.从数据库查询bgm对象,获取路径path.
                    String path = pathChildrenCacheEvent.getData().getPath();
                    String operatorObjStr = new String(pathChildrenCacheEvent.getData().getData());
                    Map<String,String> map = JsonUtils.jsonToPojo(operatorObjStr,Map.class);
                    String operatorType =  map.get("operType");
                    // bgm所在的相对路径.
                    String bgmPath = map.get("path");
//                    String bgmId = path.substring(path.lastIndexOf("/") + 1);
//                    Bgm bgm = bgmService.queryBgmById(bgmId);
//                    if (bgm == null) {
//                        return;
//                    }
//                    String bgmPath = bgm.getPath();
                    // 2.定义保存到本地的bgm路径.
                    String filePath = resourceConfig.getFileSpace() + bgmPath;
                    if (operatorType.equals(BgmOperatorType.ADD.getType())) {
                        // 3.定义下载的路径(播放url).
                        String arrPath[] = bgmPath.split("\\\\");
                        //处理url的斜杠以及编码
                        bgmPath = "";
                        for (String anArrPath : arrPath) {
                            if (StringUtils.isNotBlank(anArrPath)) {
                                bgmPath += "/";
                                bgmPath += URLEncoder.encode(anArrPath, "utf-8");
                            }
                        }
                        bgmPath = bgmPath.replaceAll("\\+", "%20");
                        String bgmUploadPath = resourceConfig.getBgmServer() + bgmPath;
                        //下载bgm到SpringBoot服务器.
                        URL url = new URL(bgmUploadPath);
                        File file = new File(filePath);
                        System.out.println("url:" + url);
                        FileUtils.copyURLToFile(url,file);
                    } else if (operatorType.equals(BgmOperatorType.DELETE.getType())) {
                        File file = new File(filePath);
                        FileUtils.forceDelete(file);
                    }
                    client.delete().forPath(path);
                }
            }
        });
    }

}
