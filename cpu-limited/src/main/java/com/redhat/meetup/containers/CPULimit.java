package com.redhat.meetup.containers;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * CPU Sample limited
 *
 */
public class CPULimit 
{
	private static final Logger logger = LoggerFactory.getLogger(CPULimit.class);
	
	private static final String CFS_PERIOD_US = "/sys/fs/cgroup/cpuacct/cpu.cfs_period_us";
	private static final String CFS_QUOTA_US = "/sys/fs/cgroup/cpuacct/cpu.cfs_quota_us";

    public static void main( String[] args ) throws Exception
    {
    	Long cfs_period_us = Long.parseLong(readFileByLine(CFS_PERIOD_US).get(0));
    	Long cfs_quota_us = Long.parseLong(readFileByLine(CFS_QUOTA_US).get(0));
    	
		logger.debug("This process can use {} ms every {} ms", cfs_quota_us==-1?"unlimited":cfs_quota_us/1000, cfs_period_us/1000);

		for(int i=0; i<10000; i++) {
        	long beg = System.currentTimeMillis();

    		//Thread.sleep(cfs_period_us/10000);

        	while(System.currentTimeMillis() < beg + cfs_period_us/10000) {
        	}
     
        	logger.debug("cycle {} completed: {}", i, System.currentTimeMillis() - beg);
        }
    }
	
    public static List<String> readFileByLine(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath), Charset.defaultCharset());
    }
}
