package com.github.ckdgus08.jobParameter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// TODO: 2021/05/20 https://youtu.be/_nkJkWVH-mo?t=1283 참고하여 jobParameter LocalDate, Enum 타입으로 사용하기

@Getter
@NoArgsConstructor
public class CreateDateJobParameter {

    private LocalDate createData;

    @Value("#{jobParameters['createDate']}")
    public void setCreateData(String createData) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.createData = LocalDate.parse(createData, formatter);
    }

}
