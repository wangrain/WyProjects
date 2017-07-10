import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * 名称：  batchTest
 * 作者:   rain.wang
 * 日期:   2016/8/24
 * 简介:
 */
public class batchTest {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring.xml");
        JobLauncher launcher = (JobLauncher) context.getBean("jobLauncher");
        Job testJob = (Job) context.getBean("testJob");

        try {
            /* 运行Job */
            Map<String, JobParameter> parameters = new HashMap<String, JobParameter>();
            JobParameter jobParameter = new JobParameter("wangyu");
            parameters.put("wy",jobParameter);
            JobExecution result = launcher.run(testJob, new JobParameters(parameters));
            /* 处理结束，控制台打印处理结果 */
            System.out.println(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
