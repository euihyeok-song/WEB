package com.ohgiraffers.chap07;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class FileUploadController {

    /* 설명. build된 곳에 파일 업로드 경로를 지정하기 위해 ResourceLoader 의존성 주입 받기*/
    private ResourceLoader resourceLoader;

    @Autowired
    public FileUploadController(ResourceLoader resourceLoader) {

        this.resourceLoader = resourceLoader;
    }

    /* 설명. multipart/form-data로 넘어온 정보는 MultipartFile로 받아낸다 - 업로드한 사진 정보들을 객체로 받음(main.html참고) */
    @PostMapping("single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile
                                  ,@RequestParam String singleFileDescription
                                  ,RedirectAttributes rttr) throws IOException {       // Redirect시 필요

        /* 설명. Get방식의 result에게 redirect한 개념
        *       => forwarding은 Insert시에 새로고침시 계속 동일한 행위가 시행될 수 있음으로 redirect 사용됨
        *   방식.
        *       1. 사진을 업로드하면 static파일에 저장되고, build파일로 올라가고 이 build된 파일이 해석되서 화면에 출력됨
        *          => 이 방식은 build 파일로 올라가는데 시간이 걸리고, 이로 인해 새로고침해야지 하면에 뜸
        *       2. ***ResourceLoader를 통해 build/resources/main/static에 파일을 올림***
        *  */
//        System.out.println("singleFile = " + singleFile);
//        System.out.println("singleFileDescription = " + singleFileDescription);

        Resource resource = resourceLoader.getResource("classpath:static/uploadFiles/img/single");
        System.out.println("파일 업로드 할 폴더의 절대 경로 = " + resource.getFile().getAbsolutePath());

        String filePath = resource.getFile().getAbsolutePath();

        String originFileName = singleFile.getOriginalFilename();                       // 원래 이름
        System.out.println("originFileName = " + originFileName);

        String ext = originFileName.substring(originFileName.lastIndexOf("."));     // 확장자 자르기
        System.out.println("ext = " + ext);

        String saveName = UUID.randomUUID().toString().replace("-","") + ext; // random이용 (저장할 이름)
        System.out.println("saveName = " + saveName);

        /* 설명. 1. 지정된 위치에 업로드된 파일 저장(feat.예외처리)
        *       2. DB에 저장할 정보 추출((원본이름,리네임이름,저장된경로,카테고리,로그인한 사람ID,용량등)-DB모델링에 따라 다름)
        * */
        try {            // transferTo 시점에서 문제 발생 가능하기 떄문 (IOException을 등록해둬도 예외 처리 진행)
            singleFile.transferTo(new File(filePath + "/" + saveName));                     // (설명 1에 해당)

            /* 설명. 원래라면 이 부분에서 Business Logic(BL) 구문 작성(서비스 계층 <-> DB)                          (설명 2에 해당)
            *       BL이 성공하면 redirect 된 페이지로 값을 넘기기 위해 RedirectAttribute로 담는다.(FlashAttribute)
            *       => static영역에 접근할 때에는 parameter를 사용하면 오류 발생 -> Attribute사 사용해야 됨 */
            rttr.addFlashAttribute("message","파일 업로드 성공!");
            /* 설명. img라는 key값을 뽑아야 result.html을 통해서 업로드시 그림을 띄울수있다.*/
            rttr.addFlashAttribute("img","uploadFiles/img/single/" + saveName);
            rttr.addFlashAttribute("singleFileDescription",singleFileDescription);


        } catch(Exception e){
            new File(filePath + "/" + saveName).delete();       // 예외 발생시 만들어진 파일을 삭제함  (설명 1에 해당)
        }

        return "redirect:/result";
    }

    @PostMapping("multi-file")
    public String multiFileUpload(@RequestParam List<MultipartFile> multiFiles      // 다중 파일을 받기위해 List
                                 ,@RequestParam String multiFileDescription
                                 ,RedirectAttributes rttr) throws IOException {

        /* 설명. 아래 resources쪽에 multi파일을 만들지 않으면 tomcat을 켜면 사라짐 -> 아래에 만들면 위(build파일)에도 반영됨*/
        String filepath = resourceLoader.getResource("classpath:static/uploadFiles/img/multi")
                                        .getFile().getAbsolutePath();

        /* 설명. Map<String,String>는 파일 하나가 지녀야 할 정보(DB속성들) => 1.Map으로 저장 2.FileUploadDTO 생성 */
//        List<PokemonInfoDTO<String,String>> files= new ArrayList<>();
        List<Map<String,String>> files= new ArrayList<>();          // DB에 저장할 값들을 지닌 List (Service 계층으로 넘길 값)
        List<String> saveFiles = new ArrayList<>();                 // 화면단에서 img 태그가 참조할 정적 리소스 경로(src 속성)

        try{
            for (int i = 0; i < multiFiles.size(); i++) {
                String originFileName = multiFiles.get(i).getOriginalFilename();                           //원본 이름
                String ext = originFileName.substring(originFileName.lastIndexOf("."));                //확장자
                String saveName = UUID.randomUUID().toString().replace("-","") + ext;    // 저장할 이름

                /* 설명. DB의 한 행에 들어갈 내용을 하나의 Map으로 추출(DB모델링 참고할 것) */
                Map<String, String> file = new HashMap<>();
                file.put("originFileName",originFileName);
                file.put("saveName",saveName);
                file.put("filePath",filepath);
                file.put("multiFileDescription", multiFileDescription);

                files.add(file);

                multiFiles.get(i).transferTo(new File(filepath + "/" + saveName));
                saveFiles.add("uploadFiles/img/multi/" + saveName);
            }

            /* 설명. redirect에 가져갈 재료 (여기까지 문제가 없었다면 성공 시 가져갈 화면의 재료 준비) */
            rttr.addFlashAttribute("message","다중 파일 업로드 성공!");
            rttr.addFlashAttribute("imgs",saveFiles);
            rttr.addFlashAttribute("multiFileDescription", multiFileDescription);

        } catch (Exception e){

            /* 설명. 전체 파일 업로드가 아닌(중간에 오류 발생) 일부 파일 업로드시 이미 올라간 파일 삭제 코드*/
            for (int i = 0; i < saveFiles.size(); i++) {   // saveFiles()는 문제 없이 파일 업로드 되었던 파일 경로가 담겨있음
                Map<String, String> file = files.get(i);
                new File(filepath + "/" + file.get("saveName")).delete();
            }
            /* 설명. 문제 발생으로 실패 시 가져갈 화면에 출력*/
            rttr.addFlashAttribute("message", "다중 파일 업로드 실패!");
        }

        return "redirect:/result";
    }

    @GetMapping("/result")
    public void result() {}
}
