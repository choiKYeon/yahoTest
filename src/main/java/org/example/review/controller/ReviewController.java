package org.example.review.controller;

import org.example.entity.Container;
import org.example.review.repository.ReviewRepository;
import org.example.screenselect.MainScreen;
import org.example.review.entity.Review;
import org.example.review.service.ReviewService;

public class ReviewController {
    ReviewRepository reviewRepository = new ReviewRepository();
    private static long id = 1;
    private static MainScreen mainScreen = new MainScreen();
    ReviewService reviewService = new ReviewService();

    public void write() {

        System.out.println("\n== 리뷰를 작성해주세요. ==");
        System.out.print("제목 :");
        String title = Container.getSc().nextLine().trim();
        System.out.print("내용 :");
        String content = Container.getSc().nextLine().trim();

        String userId = Container.getCheckedmembers().getUserId();

        reviewService.writeService(id, title, content, userId);

        System.out.println(Container.getCheckedmembers().getUserId() + "님의 리뷰가 작성되었습니다.");
        id++;

        mainScreen.mainSelect();
    }
    public void fullReview() {
        System.out.println("리뷰 번호 / 제목 / 내용 / 작성자");
        System.out.println("-".repeat(29));

        reviewService.listService();

        System.out.println("리뷰 목록이 출력되었습니다.");
        mainScreen.mainSelect();
    }
    public void myReview() {

        if (reviewRepository.getReviews().size() == 0) {
            System.out.println("작성한 리뷰가 없습니다.");
            mainScreen.mainSelect();
            return;
        }

        reviewService.myReviewListService();

        for (int i = 0; i < reviewRepository.getReviews().size(); i++) {
            if (ReviewRepository.getReviews().get(i).getUserId().equals(Container.getCheckedmembers().getUserId()) == false) {
                System.out.println("작성한 리뷰가 없습니다.");
            }else {
                System.out.println("나의 리뷰 목록이 출력되었습니다.");
            }
            break;
        }
        mainScreen.mainSelect();
    }
    public void modify() {

        if (reviewRepository.getReviews().size() == 0) {
            System.out.println("작성한 리뷰가 없습니다.");
            mainScreen.mainSelect();
            return;
        }
        reviewService.myReviewListService();

        for (int i = 0; i < reviewRepository.getReviews().size(); i++) {
            if (ReviewRepository.getReviews().get(i).getUserId().equals(Container.getCheckedmembers().getUserId()) == false) {
                System.out.println("작성한 리뷰가 없습니다.");
            } else {
                System.out.println("나의 리뷰 목록이 출력되었습니다. 수정할 리뷰번호를 선택해주세요.");
                System.out.print("입력 :");
                int id = Container.getSc().nextInt();
                Container.getSc().nextLine().trim();

                Review review = reviewService.getfindByIdService(id);

                if (review == null) {
                    System.out.println("다시 입력해주세요");
                    return;
                }

                System.out.printf("기존 제목 : %s\n", review.getTitle());
                System.out.printf("제목 : ");

                String modifyTitle = Container.getSc().nextLine().trim();

                System.out.printf("기존 내용 : %s\n", review.getContent());
                System.out.printf("내용 : ");

                String modifyContent = Container.getSc().nextLine().trim();

                reviewService.modifyService(review, modifyTitle, modifyContent);

                System.out.println(id + "번 리뷰가 수정되었습니다.");
            }
            break;
        }
        mainScreen.mainSelect();
    }
    public void remove() {

        if (reviewRepository.getReviews().size() == 0) {
            System.out.println("작성한 리뷰가 없습니다.");
            mainScreen.mainSelect();
            return;
        }

        reviewService.myReviewListService();

        for (int i = 0; i < reviewRepository.getReviews().size(); i++) {
            if (ReviewRepository.getReviews().get(i).getUserId().equals(Container.getCheckedmembers().getUserId()) == false) {
                System.out.println("작성한 리뷰가 없습니다.");
            } else {
                System.out.println("나의 리뷰 목록이 출력되었습니다. 삭제할 리뷰번호를 선택해주세요.");
                System.out.print("입력 :");
                int id = Container.getSc().nextInt();

                Review review = reviewService.getfindByIdService(id);

                if (review == null) {
                    System.out.println("다시 입력해주세요");
                    return;
                }

                reviewService.removeService(review);

                System.out.println(id + "번 리뷰가 삭제되었습니다.");

                Container.getSc().nextLine().trim();
            }
            break;
        }
        mainScreen.mainSelect();
    }
}