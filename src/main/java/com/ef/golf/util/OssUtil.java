package com.ef.golf.util;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.cloopen.rest.sdk.utils.PropertiesUtil;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class OssUtil {

    private static String endpoint;
    private static String accessKeyId;
    private static String accessKeySecret;



    public static void initPro() {
        Properties properties=new Properties();

        InputStream in= PropertiesUtil.class.getClassLoader().getResourceAsStream("oss.properties");

        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // endpoint以杭州为例，其它region请按实际情况填写
          endpoint =properties.getProperty("endPoint");
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建
          accessKeyId = properties.getProperty("accessKeyId");
          accessKeySecret = properties.getProperty("accessKeySecret");
    }

    static {
        initPro();
    }
    public static void deleteObjects(List<String>keys,String bucketName){

        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 删除Objects
            DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(keys));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
        // 关闭client
        ossClient.shutdown();
    }



    public static void upload(String yourKey, File file, String bucketname){
        // 创建ClientConfiguration实例
        ClientConfiguration conf = new ClientConfiguration();

        // 设置OSSClient使用的最大连接数，默认1024
        conf.setMaxConnections(200);
        // 设置请求超时时间，默认50秒
        conf.setSocketTimeout(10000);
        // 设置失败请求重试次数，默认3次
        conf.setMaxErrorRetry(5);

        // 创建OSSClient实例
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
        // 使用访问OSS

        client.putObject(bucketname, yourKey, file);

        // 关闭OSSClient
        client.shutdown();
    }

    public static void delete(String yourKey,String bucketname) {
        // 创建ClientConfiguration实例
        ClientConfiguration conf = new ClientConfiguration();

        // 设置OSSClient使用的最大连接数，默认1024
        conf.setMaxConnections(200);
        // 设置请求超时时间，默认50秒
        conf.setSocketTimeout(10000);
        // 设置失败请求重试次数，默认3次
        conf.setMaxErrorRetry(5);

        // 创建OSSClient实例
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret, conf);
        // 使用访问OSS
        if (StringUtils.isNotEmpty(yourKey)) {
            String[] arr = yourKey.split("/");
            yourKey = arr[arr.length - 1];
            // Object是否存在
            boolean found = client.doesObjectExist(bucketname, yourKey);
            System.out.println(found);
            if (found == true) {
                client.deleteObject(bucketname, yourKey);
            }
        }
        // 关闭OSSClient
        client.shutdown();
    }
}
