package my.example.container;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChannelResponse {

    @JsonProperty("channels")
    private List<Channel> channels;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Channel {
        private String id;

        private String name;

        private Boolean system;
    }

}
