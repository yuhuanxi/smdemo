package com.ysp.smdemo.common.configs;

import org.apache.ibatis.io.VFS;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author: shipeng.yu
 * @time: 2016年12月22日 下午7:55
 * @version: 1.0
 * @since: 1.0
 * @description:
 */
public class SpringBootVFS extends VFS {

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    protected List<String> list(URL url, String forPath) throws IOException {

        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(
                forPath + "/**/*.class");

        return Arrays.asList(resources).stream().map(resource -> {
            try {
                URI uri = resource.getURI();
                String uriAsString = uri.toString();
                String finalValue = uriAsString.substring(uriAsString.indexOf(forPath));
                return finalValue;
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }).collect(toList());
    }
}
