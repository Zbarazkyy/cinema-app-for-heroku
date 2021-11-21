package cinema.app.dto.response;

import lombok.Data;

@Data
public class MovieSessionResponseDto {
    private Long movieSessionId;
    private Long movieId;
    private String movieTitle;
    private Long cinemaHallId;
    private String showTime;
}
