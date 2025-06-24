package com.jobee.admin.service.application.analysis;

import com.jobee.admin.service.application.usecases.reviewanalysis.CreateReviewAnalysisInputDto;
import com.jobee.admin.service.application.usecases.reviewanalysis.CreateReviewAnalysisUseCase;
import com.jobee.admin.service.domain.reviewanalysis.ReviewAnalysisBuilder;
import com.jobee.admin.service.domain.reviewanalysis.ReviewAnalysisRepository;
import com.jobee.admin.service.domain.reviewanalysis.TypeAnalysis;
import com.jobee.admin.service.domain.validation.Error;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateReviewAnalysisUseCaseTest {

    @InjectMocks
    private CreateReviewAnalysisUseCase sut;

    @Mock
    private ReviewAnalysisRepository repository;

    @BeforeEach
    public void cleanup() {
        Mockito.reset(repository);
    }

    @Test
    public void testInjectDependencies() {
        Assertions.assertNotNull(sut);
        Assertions.assertNotNull(repository);
    }

    @Test
    public void giveAnValidCommand_whenCallCreateReviewAnalysisUseCase_thenShouldReturnInstance() {
        final var expectedUserId = "2f9d7582d2164f43b9ce14ed2b4903b1";
        final var expectedReviewId = "aad74d08dad743f088182f15cc1cea7b";
        final var expectedType = "CREATE";

        final var expectedReviewAnalysis = new ReviewAnalysisBuilder(
                expectedUserId,
                expectedReviewId,
                TypeAnalysis.CREATE
        ).build();

//        when(repository.create(any())).thenReturn(                                                             );

        final var outputData = sut
                .execute(CreateReviewAnalysisInputDto.from(expectedUserId, expectedReviewId, expectedType))
                .get();

        Assertions.assertFalse(outputData.getNotification().hasError());
        Assertions.assertEquals(expectedReviewAnalysis.getReviewId(), outputData.getReviewId());
        Assertions.assertEquals(expectedReviewAnalysis.getUserId(), outputData.getUserId());
        Assertions.assertNull(expectedReviewAnalysis.getStartAt());
        Assertions.assertNull((expectedReviewAnalysis.getEndAt()));

        Mockito.verify(repository, Mockito.times(1)).create(
                argThat(data -> Objects.equals(expectedReviewAnalysis.getUserId(), data.getUserId())
                        && Objects.equals(expectedReviewAnalysis.getReviewId(), data.getReviewId())
                        && Objects.nonNull(data.getId())
                        && Objects.isNull(data.getStartAt())
                        && Objects.isNull(data.getEndAt())
                )
        );
    }

    @Test
    public void giveAnInValidCommand_whenCallCreateReviewAnalysisUseCase_thenShouldReturnError() {
        final var expectedUserId = "2f9d7582d2164f";
        final var expectedReviewId = "aad74d08dad743f088182f15cc1cea7b";
        final var expectedType = "CREATE";
        final var expectedTotalError = 1;

//        new ReviewAnalysisBuilder(
//                expectedUserId,
//                expectedReviewId,
//                TypeAnalysis.CREATE
//        ).build();

        final var outputData = sut
                .execute(CreateReviewAnalysisInputDto.from(expectedUserId, expectedReviewId, expectedType))
                .getLeft();

        Assertions.assertEquals(expectedTotalError, outputData.getErrors().size());
        Assertions.assertEquals(outputData.getErrors(), List.of(new Error("UserId must be a valid UUID")));
        Mockito.verify(repository, Mockito.times(0)).create(any());
    }
}