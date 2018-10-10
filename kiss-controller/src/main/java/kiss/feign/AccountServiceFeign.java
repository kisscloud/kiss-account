package kiss.feign;

import com.kiss.account.client.AccountClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
@FeignClient("kiss-account")
public interface AccountServiceFeign extends AccountClient {
}
