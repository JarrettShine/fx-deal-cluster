package com.demo.controller;


import com.demo.dto.DealRequest;
import com.demo.dto.DealResponse;
import com.demo.service.DealService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Slf4j
@RestController
@RequestMapping("/v1/dealers")
@Validated
public class DealController {

    private final Gson gson;
    private ObjectMapper mapper;
    private final DealService dealService;
    public DealController(DealService dealService,Gson gson,ObjectMapper mapper) {
        this.dealService = dealService;
        this.gson = gson;
        this.mapper = mapper;
    }
        @Operation(
            summary = "Create fx deal cluster",
            description = "Accept deals details from customer and persist them into DB."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    content = {@Content(
                            mediaType = "application/json"
                    )},
                    responseCode = "200",
                    description = "OK"
            ),
            @ApiResponse(
                    content = {@Content(mediaType = "application/json")},
                    responseCode = "400",
                    description = "Bad Request, invalid format of the request. See response message for more information"
            ),
            @ApiResponse(
                    content = {@Content(mediaType = "application/json")},
                    responseCode = "404",
                    description = "Not Found"
            ),
            @ApiResponse(
                    content = {@Content(mediaType = "application/json")},
                    responseCode = "422",
                    description = "Unprocessable Entity"
            ),
            @ApiResponse(
                    content = {@Content(mediaType = "application/json")},
                    responseCode = "500",
                    description = "Internal Server Error"
            ),
            @ApiResponse(
                    content = {@Content(mediaType = "application/json")},
                    responseCode = "503",
                    description = "Service Unavailable"
            ),
    })
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = {@Content(mediaType = "application/json")}
    )
    @PostMapping("/deals")
    public ResponseEntity<DealResponse> createDeal(@Valid @RequestBody DealRequest dealRequest) throws
            JsonProcessingException {

        try {
            log.info("Deal request: {} " + gson.toJson(mapper.writeValueAsString(dealRequest)));
            DealResponse response = dealService.saveDeal(dealRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ResponseStatusException e) {
            log.error("Exception ex: {} " + e);
            int statusCode = e.getStatusCode().value();
            HttpStatus status = HttpStatus.valueOf(statusCode);
            DealResponse errorResponseDTO = new DealResponse(
                    status.value(),
                    e.getReason(),
                    e.getMessage(),
                    null
            );
            return ResponseEntity.status(status).body(errorResponseDTO);
        }
    }

    private static final String EVENT_DOWNTIME_RESTORED_MESSAGE = """
                                          <!DOCTYPE html>
                                          <html lang="en">
                                            <head>
                                              <meta charset="UTF-8" />
                                              <meta name="viewport" content="width=device-width, initial-scale=1.0" />
                                              <style>
                                                html {
                                                  width: fit-content;
                                                  margin: 0 auto;
                                                  padding: 0;
                                                }
                                          
                                                body {
                                                  font-family: Arial, sans-serif;
                                                  margin: 0 auto;
                                                  background: white;
                                                  width: 100%;
                                                  max-width: 600px;
                                                }
                                          
                                                table {
                                                  margin: 0 auto;
                                                  padding: 20px 0px;
                                                }
                                          
                                                .grid-cell {
                                                  padding: 10px;
                                                  width: 33%;
                                                  border: 1px solid #f5f5f5;
                                                  background: #f5f5f5;
                                                  display: flex;
                                                  flex-direction: column;
                                                  gap: 8px;
                                                  align-items: flex-start;
                                                  justify-content: space-between;
                                                  border-radius: 5px;
                                                }
                                              </style>
                                            </head>
                                          
                                            <body>
                                              <table
                                                width="100%"
                                                cellpadding="0"
                                                cellspacing="0"
                                                style="border-collapse: collapse; margin-top: 30px"
                                              >
                                                <tr>
                                                  <td style="padding: 10px; text-align: center">
                                                    <img
                                                      src="https://d11ylb77sv0744.cloudfront.net/images/pngs/logo-oxygen.png"
                                                      alt="oxygen-logo"
                                                    />
                                                    <div style="margin-top: -5px">
                                                      <svg
                                                        width="70"
                                                        height="11"
                                                        viewBox="0 0 70 11"
                                                        fill="none"
                                                        xmlns="http://www.w3.org/2000/svg"
                                                      >
                                                        <path
                                                          d="M28.6891 5.46115L24.0016 1.04102L23.5425 1.44284L28.3543 6.03711V10.7787H29.0191V6.02371L33.8165 1.44284L33.3525 1.04102L28.6891 5.46115Z"
                                                          fill="#002639"
                                                        />
                                                        <path
                                                          d="M47.5147 10.7815H57.4874V10.1921H48.1843V6.57565H57.4874V5.98183H48.1843V1.77154H57.4874V1.17773H47.5147V10.7815Z"
                                                          fill="#002639"
                                                        />
                                                        <path
                                                          d="M69.3302 1.17773V9.79029L60.4385 1.17773H59.8693V10.7815H60.5341V2.17337L69.4259 10.7815H69.9998V1.17773H69.3302Z"
                                                          fill="#002639"
                                                        />
                                                        <path
                                                          d="M44.8271 5.976H40.0154V6.58768H44.7793C44.4445 8.74417 42.383 10.4095 40.0154 10.4095C37.4278 10.4095 35.3136 8.4227 35.3136 5.976C35.3136 3.5293 37.423 1.54248 40.0154 1.54248C41.7899 1.54248 43.3396 2.47562 44.1384 3.85078L44.7171 3.53377C43.7988 1.99342 42.0338 0.953125 40.0154 0.953125C37.0451 0.953125 34.6345 3.20784 34.6345 5.976C34.6345 8.74417 37.0499 10.9989 40.0154 10.9989C42.9809 10.9989 45.5111 8.74417 45.5111 5.976H44.8271Z"
                                                          fill="#002639"
                                                        />
                                                        <path
                                                          d="M5.38118 0.943359C8.35145 0.943359 10.7621 3.19807 10.7621 5.96624C10.7621 8.7344 8.34667 10.9891 5.38118 10.9891C2.41569 10.9891 0.000244141 8.7344 0.000244141 5.96624C0.000244141 3.19807 2.41569 0.943359 5.38118 0.943359ZM5.38118 10.3998C7.96881 10.3998 10.0829 8.41294 10.0829 5.96624C10.0829 3.51954 7.97359 1.53271 5.38118 1.53271C2.78876 1.53271 0.679459 3.51954 0.679459 5.96624C0.679459 8.41294 2.78876 10.3998 5.38118 10.3998Z"
                                                          fill="#002639"
                                                        />
                                                        <path
                                                          d="M11.7136 10.4257L16.334 5.96539L11.6801 1.47382L12.1249 1.02734L16.7932 5.53231L21.4614 1.02734L21.9062 1.47382L17.238 5.96539L21.8728 10.4257L21.4279 10.8722L16.7932 6.41187L12.1584 10.8722L11.7136 10.4257Z"
                                                          fill="#002639"
                                                        />
                                                        <path
                                                          d="M33.3525 1.03125L33.8117 1.43308L29.0191 6.01395V10.7734H28.3543V6.0318L23.5425 1.43755L24.0016 1.03572L28.6843 5.45138L33.3525 1.03572V1.03125Z"
                                                          fill="#002639"
                                                        />
                                                        <path
                                                          d="M69.4122 10.7678L60.5205 2.15524V10.7678H59.8557V1.16406H60.4248L69.3165 9.77662V1.16406H69.9813V10.7678H69.4122Z"
                                                          fill="#002639"
                                                        />
                                                      </svg>
                                                    </div>
                                                  </td>
                                                </tr>
                                              </table>
                                          
                                              <table width="100%" cellpadding="0" cellspacing="0">
                                                <tr>
                                                  <td>
                                                    <table
                                                      width="100%"
                                                      height="520px"
                                                      cellpadding="0"
                                                      cellspacing="0"
                                                      style="
                                                        /* background-image: url(https://dyfe6x6hjjd15.cloudfront.net/images/pngs/frame-1.png);
                                                        background-size: 520px 520px;
                                                        background-repeat: no-repeat;
                                                        background-position: center;
                                                        padding-top: 32px; */
                                                        box-shadow: 3px 3px 8px 0px #63636333;
                                                        position: relative;
                                                      "
                                                    >
                                                      <tr style="position: absolute; top: 15px; z-index: -1">
                                                        <td>
                                                          <svg
                                                            width="62"
                                                            height="163"
                                                            viewBox="0 0 62 163"
                                                            fill="none"
                                                            xmlns="http://www.w3.org/2000/svg"
                                                          >
                                                            <path
                                                              d="M-70.7607 18.0509C-87.013 31.1614 -97.5743 50.0418 -100.241 70.752C-102.907 91.4621 -97.4726 112.402 -85.0712 129.202C-72.6698 146.001 -54.2597 157.363 -33.6829 160.915C-13.1062 164.467 8.04747 159.935 25.3634 148.266C42.6794 136.596 54.8199 118.69 59.2512 98.2848C63.6825 77.8793 60.0623 56.5508 49.1461 38.7503C38.23 20.9497 20.8612 8.05257 0.664512 2.75021C-19.5322 -2.55213 -40.9963 0.150017 -59.2486 10.2928L-49.4006 28.0146C-35.7169 20.4106 -19.6252 18.3848 -4.48375 22.36C10.6577 26.3351 23.6791 36.0042 31.8629 49.3492C40.0468 62.6942 42.7608 78.6842 39.4387 93.9822C36.1165 109.28 27.0148 122.704 14.033 131.453C1.05125 140.202 -14.8076 143.599 -30.234 140.936C-45.6604 138.273 -59.4625 129.755 -68.7598 117.161C-78.0571 104.566 -82.1314 88.8673 -80.1324 73.3409C-78.1334 57.8145 -70.2156 43.6599 -58.0312 33.8309L-70.7607 18.0509Z"
                                                              fill="#FF660A"
                                                            />
                                                            <path
                                                              d="M-70.7607 18.0509C-87.013 31.1614 -97.5743 50.0418 -100.241 70.752C-102.907 91.4621 -97.4726 112.402 -85.0712 129.202C-72.6698 146.001 -54.2597 157.363 -33.6829 160.915C-13.1062 164.467 8.04747 159.935 25.3634 148.266C42.6794 136.596 54.8199 118.69 59.2512 98.2848C63.6825 77.8793 60.0623 56.5508 49.1461 38.7503C38.23 20.9497 20.8612 8.05257 0.664512 2.75021C-19.5322 -2.55213 -40.9963 0.150017 -59.2486 10.2928L-49.4006 28.0146C-35.7169 20.4106 -19.6252 18.3848 -4.48375 22.36C10.6577 26.3351 23.6791 36.0042 31.8629 49.3492C40.0468 62.6942 42.7608 78.6842 39.4387 93.9822C36.1165 109.28 27.0148 122.704 14.033 131.453C1.05125 140.202 -14.8076 143.599 -30.234 140.936C-45.6604 138.273 -59.4625 129.755 -68.7598 117.161C-78.0571 104.566 -82.1314 88.8673 -80.1324 73.3409C-78.1334 57.8145 -70.2156 43.6599 -58.0312 33.8309L-70.7607 18.0509Z"
                                                              fill="white"
                                                              fill-opacity="0.85"
                                                            />
                                                          </svg>
                                                        </td>
                                                      </tr>
                                                      <tr>
                                                        <td>
                                                          <h2
                                                            style="
                                                              font-size: 14px;
                                                              font-weight: 700;
                                                              line-height: 18px;
                                                              letter-spacing: 0.15px;
                                                              color: #002639;
                                                              width: 80%;
                                                              margin: 0px auto;
                                                              padding-bottom: 5px;
                                                              text-align: center;
                                                            "
                                                          >
                                                            Downtime Restored!
                                                          </h2>
                                                        </td>
                                                      </tr>
                                                      <tr>
                                                        <td style="text-align: center">
                                                          <img
                                                            src="https://dyfe6x6hjjd15.cloudfront.net/images/gifs/Animation%20-%201715092029737.gif"
                                                            alt="downtime-restored"
                                                            width="120"
                                                            height="120"
                                                          />
                                                        </td>
                                                      </tr>
                                                      <tr>
                                                        <td>
                                                          <p
                                                            style="
                                                              font-size: 10px;
                                                              font-weight: 700;
                                                              line-height: 13px;
                                                              letter-spacing: 0.15px;
                                                              color: #395a6b;
                                                              width: 80%;
                                                              margin: 5px auto;
                                                            "
                                                          >
                                                            <b>Dear {customer's name},</b>
                                                          </p>
                                                        </td>
                                                      </tr>
                                                      <tr>
                                                        <td>
                                                          <p
                                                            style="
                                                              font-size: 11px;
                                                              font-weight: 500;
                                                              line-height: 13px;
                                                              letter-spacing: 0.4px;
                                                              color: #395a6b;
                                                              width: 80%;
                                                              margin: 0px auto;
                                                              padding-bottom: 5px;
                                                            "
                                                          >
                                                            We're pleased to inform you that the scheduled downtime for
                                                            maintenance and improvements on the Oxygen platform has been
                                                            successfully completed, and all services are now restored to
                                                            full functionality.
                                                          </p>
                                                        </td>
                                                      </tr>
                                                      <tr>
                                                        <td>
                                                          <p
                                                            style="
                                                              font-size: 11px;
                                                              font-weight: 500;
                                                              line-height: 13px;
                                                              letter-spacing: 0.4px;
                                                              color: #395a6b;
                                                              width: 80%;
                                                              margin: 0px auto;
                                                              padding-bottom: 5px;
                                                            "
                                                          >
                                                            We apologize for any inconvenience caused during this period
                                                            and appreciate your patience and understanding. If you
                                                            encounter any issues or need further assistance, please don't
                                                            hesitate to reach out to our support team at [Support
                                                            Email/Phone Number].
                                                          </p>
                                                        </td>
                                                      </tr>
                                                      <tr>
                                                        <td>
                                                          <p
                                                            style="
                                                              font-size: 11px;
                                                              font-weight: 500;
                                                              line-height: 13px;
                                                              letter-spacing: 0.4px;
                                                              color: #395a6b;
                                                              width: 80%;
                                                              margin: 0px auto;
                                                              padding-bottom: 5px;
                                                            "
                                                          >
                                                            Thank you for your continued support and cooperation.
                                                          </p>
                                                        </td>
                                                      </tr>
                                                      <tr>
                                                        <td>
                                                          <p
                                                            style="
                                                              font-size: 11px;
                                                              font-weight: 500;
                                                              line-height: 13px;
                                                              letter-spacing: 0.4px;
                                                              color: #395a6b;
                                                              width: 80%;
                                                              margin: 0px auto;
                                                              padding: 10px 0px;
                                                            "
                                                          >
                                                            Best regards,
                                                          </p>
                                                        </td>
                                                      </tr>
                                                      <tr>
                                                        <td>
                                                          <p
                                                            style="
                                                              font-size: 11px;
                                                              font-weight: 700;
                                                              line-height: 13px;
                                                              letter-spacing: 0.4px;
                                                              color: #395a6b;
                                                              width: 80%;
                                                              margin: 0px auto;
                                                              padding-bottom: 5px;
                                                            "
                                                          >
                                                            Team Oxygen X
                                                          </p>
                                                        </td>
                                                      </tr>
                                                      <tr
                                                        style="position: absolute; bottom: 15px; right: 0px; z-index: -1"
                                                      >
                                                        <td>
                                                          <svg
                                                            width="42"
                                                            height="163"
                                                            viewBox="0 0 42 163"
                                                            fill="none"
                                                            xmlns="http://www.w3.org/2000/svg"
                                                          >
                                                            <path
                                                              opacity="0.85"
                                                              d="M148.17 126.725C159.91 109.456 164.527 88.3213 161.059 67.7303C157.591 47.1392 146.304 28.683 129.555 16.2133C112.807 3.74352 91.889 -1.7763 71.1682 0.805714C50.4473 3.38773 31.5241 13.8721 18.3475 30.0708C5.17094 46.2696 -1.24087 66.9311 0.450134 87.7437C2.14114 108.556 11.8043 127.912 27.4231 141.771C43.0418 155.63 63.4094 162.922 84.2753 162.125C105.141 161.328 124.893 152.504 139.409 137.494L124.836 123.4C113.953 134.653 99.1447 141.268 83.5016 141.866C67.8584 142.463 52.5888 136.996 40.8795 126.606C29.1701 116.216 21.9256 101.705 20.6578 86.1018C19.3901 70.4986 24.197 55.0087 34.0755 42.8645C43.954 30.7203 58.1407 22.8602 73.6752 20.9244C89.2096 18.9887 104.891 23.1269 117.448 32.4755C130.005 41.824 138.466 55.6606 141.066 71.0978C143.666 86.5349 140.205 102.38 131.403 115.326L148.17 126.725Z"
                                                              fill="#2BD9C7"
                                                              fill-opacity="0.3"
                                                            />
                                                          </svg>
                                                        </td>
                                                      </tr>
                                                    </table>
                                                  </td>
                                                </tr>
                                              </table>
                                          
                                              <table
                                                width="100%"
                                                cellpadding="0"
                                                cellspacing="0"
                                                style="margin-top: 20px"
                                              >
                                                <tr>
                                                  <td style="text-align: center">
                                                    <span style="margin-right: 4px">
                                                      <a href="" style="text-decoration: none">
                                                        <svg
                                                          width="100"
                                                          height="34"
                                                          viewBox="0 0 100 34"
                                                          fill="none"
                                                          xmlns="http://www.w3.org/2000/svg"
                                                        >
                                                          <path
                                                            d="M92.0366 0.000108637H7.96787C7.66143 0.000108637 7.35867 0.000108637 7.05304 0.00177998C6.79719 0.00345133 6.54339 0.00830659 6.28509 0.012393C5.72395 0.0189963 5.16413 0.0683633 4.61049 0.160065C4.05763 0.253763 3.52209 0.43041 3.02198 0.684032C2.52248 0.939799 2.06607 1.27214 1.66931 1.66901C1.27048 2.06475 0.938012 2.52218 0.684708 3.02371C0.430706 3.52423 0.25458 4.06059 0.162413 4.61426C0.06937 5.16723 0.0193027 5.72657 0.0126604 6.28728C0.0049054 6.54349 0.00408644 6.80056 0 7.05681V26.3728C0.00408644 26.6322 0.0049054 26.8836 0.0126604 27.1432C0.0193047 27.7039 0.069372 28.2632 0.162413 28.8161C0.254325 29.3701 0.430462 29.9068 0.684708 30.4075C0.937897 30.9074 1.27041 31.363 1.66931 31.7565C2.06457 32.1551 2.52127 32.4877 3.02198 32.7415C3.52208 32.9958 4.05757 33.1735 4.61049 33.2687C5.16422 33.3596 5.72399 33.409 6.28509 33.4164C6.54339 33.4221 6.79719 33.4253 7.05304 33.4253C7.35866 33.427 7.66145 33.427 7.96787 33.427H92.0366C92.337 33.427 92.6422 33.427 92.9425 33.4253C93.1971 33.4253 93.4583 33.4221 93.7129 33.4164C94.2729 33.4094 94.8317 33.36 95.3842 33.2687C95.9389 33.1728 96.4764 32.9952 96.9789 32.7415C97.4791 32.4876 97.9354 32.155 98.3303 31.7565C98.7282 31.3614 99.0614 30.9062 99.3178 30.4075C99.5701 29.9064 99.7446 29.3698 99.8351 28.8161C99.9283 28.2631 99.9802 27.7039 99.9902 27.1432C99.9935 26.8836 99.9935 26.6322 99.9935 26.3728C100 26.0692 100 25.7672 100 25.4587V7.96919C100 7.66316 100 7.35958 99.9935 7.05681C99.9935 6.80056 99.9935 6.54349 99.9902 6.28724C99.9802 5.72649 99.9283 5.16727 99.8351 4.61423C99.7443 4.06088 99.5699 3.52456 99.3178 3.02367C98.8019 2.01842 97.9839 1.20016 96.9789 0.683956C96.4763 0.430954 95.9389 0.254354 95.3842 0.15999C94.8318 0.067884 94.273 0.0184999 93.7129 0.012276C93.4583 0.00819795 93.1971 0.00330091 92.9425 0.00167135C92.6422 0 92.337 0 92.0366 0V0.000108637Z"
                                                            fill="#A6A6A6"
                                                          />
                                                          <path
                                                            d="M7.05693 32.695C6.80232 32.695 6.55386 32.6917 6.30124 32.686C5.77793 32.6792 5.25584 32.6336 4.73925 32.5497C4.25756 32.4668 3.79094 32.3124 3.35476 32.0918C2.92259 31.8731 2.52841 31.5862 2.18733 31.2423C1.84131 30.9024 1.55323 30.5082 1.33451 30.0753C1.11339 29.6396 0.960352 29.1725 0.880744 28.6904C0.794774 28.1724 0.74826 27.6486 0.741604 27.1235C0.736306 26.9473 0.72937 26.3605 0.72937 26.3605V7.05596C0.72937 7.05596 0.736757 6.47817 0.741646 6.30842C0.748019 5.78418 0.794262 5.2612 0.879975 4.74398C0.959731 4.26056 1.11288 3.79216 1.33413 3.35501C1.55204 2.92239 1.83852 2.52788 2.18245 2.18678C2.526 1.84243 2.92144 1.55411 3.35435 1.33233C3.78952 1.11245 4.25527 0.95922 4.73599 0.877773C5.25427 0.793007 5.77817 0.747179 6.3033 0.740672L7.05734 0.730469H92.9382L93.7013 0.741082C94.2216 0.747265 94.7408 0.792685 95.2543 0.876954C95.7399 0.959422 96.2105 1.11372 96.6506 1.33478C97.5179 1.78173 98.2236 2.48886 98.6688 3.35705C98.8865 3.79119 99.0373 4.25576 99.116 4.735C99.2027 5.25647 99.2513 5.78356 99.2613 6.31209C99.2637 6.54876 99.2637 6.80297 99.2637 7.05596C99.2703 7.36934 99.2703 7.66761 99.2703 7.96834V25.4579C99.2703 25.7615 99.2703 26.0577 99.2637 26.3564C99.2637 26.6281 99.2637 26.8771 99.2604 27.1333C99.2507 27.6524 99.2029 28.1701 99.1176 28.6822C99.0396 29.1678 98.8874 29.6385 98.6663 30.0778C98.4461 30.506 98.1597 30.8968 97.8176 31.2358C97.4762 31.5816 97.0814 31.8701 96.6482 32.0903C96.2093 32.3125 95.7394 32.4674 95.2543 32.5497C94.7377 32.6341 94.2156 32.6796 93.6922 32.686C93.4475 32.6917 93.1912 32.695 92.9423 32.695L92.0364 32.6966L7.05693 32.695Z"
                                                            fill="black"
                                                          />
                                                          <path
                                                            fill-rule="evenodd"
                                                            clip-rule="evenodd"
                                                            d="M62.4068 24.2974C61.0407 24.2974 60.041 23.6217 59.9259 22.5926L58.3362 22.5925C58.4366 24.4695 59.9904 25.6569 62.2918 25.6569C64.7514 25.6569 66.2979 24.441 66.2979 22.4995C66.2979 20.9816 65.4133 20.1263 63.2841 19.6228L62.1408 19.3421C60.8032 19.0116 60.2564 18.5799 60.2564 17.8462C60.2564 16.9257 61.1125 16.2932 62.3636 16.2932C63.6146 16.2932 64.4707 16.9183 64.5646 17.9539H66.1323C66.0817 16.1634 64.5784 14.9336 62.3856 14.9336C60.17 14.9336 58.6096 16.1634 58.6096 17.9539C58.6096 19.4 59.4942 20.2912 61.3998 20.7515L62.7447 21.0755C64.0896 21.406 64.6576 21.8883 64.6576 22.6938C64.6576 23.6217 63.7159 24.2974 62.4068 24.2974ZM35.3505 22.6791H31.3949L30.445 25.484H28.7695L32.5162 15.1066H34.2569L38.0035 25.484H36.2996L35.3505 22.6791ZM31.8048 21.3849H34.9402L33.3946 16.8328H33.3513L31.8048 21.3849ZM42.9375 25.5632C44.8365 25.5632 46.0949 24.0526 46.0949 21.7014C46.0949 19.3568 44.8291 17.8389 42.9089 17.8389C41.9208 17.807 40.9952 18.3209 40.4998 19.1765H40.4713V17.9181H38.968V27.9869H40.521V24.2395H40.557C41.0313 25.1004 41.9559 25.6145 42.9375 25.5632ZM44.4987 21.7014C44.4987 20.1697 43.7071 19.1626 42.4993 19.1626C41.3127 19.1626 40.5146 20.1909 40.5146 21.7014C40.5146 23.2259 41.3127 24.2468 42.4993 24.2468C43.7071 24.2468 44.4987 23.2471 44.4987 21.7014ZM51.2645 25.5631C53.1635 25.5631 54.4219 24.0525 54.4219 21.7013C54.4219 19.3567 53.1562 17.8388 51.2359 17.8388C50.2478 17.8069 49.3222 18.3208 48.8268 19.1764H48.7982V17.918H47.295V27.9868H48.848V24.2394H48.884C49.3583 25.1003 50.2829 25.6144 51.2645 25.5631ZM52.8262 21.7014C52.8262 20.1697 52.0346 19.1626 50.8268 19.1626C49.6402 19.1626 48.842 20.1909 48.842 21.7014C48.842 23.2259 49.6402 24.2468 50.8268 24.2468C52.0346 24.2468 52.8262 23.2471 52.8262 21.7014ZM69.6496 16.1276V17.9181H71.0884V19.1479H69.6496V23.3189C69.6496 23.9669 69.9377 24.2689 70.5702 24.2689C70.741 24.2659 70.9115 24.2539 71.081 24.2329V25.4554C70.7967 25.5085 70.5076 25.5326 70.2184 25.5272C68.6866 25.5272 68.0893 24.9519 68.0893 23.4846V19.1479H66.9892V17.9181H68.0892V16.1276H69.6496ZM75.5103 17.825C73.324 17.825 71.922 19.3208 71.922 21.7014C71.922 24.0884 73.3101 25.5778 75.5103 25.5778C77.7113 25.5778 79.0995 24.0884 79.0995 21.7014C79.0995 19.3208 77.7039 17.825 75.5103 17.825ZM77.5168 21.7015C77.5168 20.0685 76.7685 19.1047 75.5101 19.1047C74.2517 19.1047 73.5041 20.0758 73.5041 21.7015C73.5041 23.341 74.2517 24.2974 75.5101 24.2974C76.7685 24.2974 77.5168 23.341 77.5168 21.7015H77.5168ZM81.8612 17.9181H80.38L80.38 25.4841H81.933V20.9964C81.8813 20.5424 82.0306 20.0885 82.3417 19.7538C82.6529 19.4191 83.0947 19.2372 83.5513 19.2557C83.7874 19.2488 84.0231 19.2805 84.2491 19.3496V17.8969C84.0744 17.8578 83.896 17.8383 83.717 17.839C82.8633 17.8064 82.1037 18.3769 81.8971 19.2059H81.8612V17.9181ZM91.4089 23.2618C91.2 24.6353 89.8624 25.5778 88.1511 25.5778C85.9501 25.5778 84.584 24.1032 84.584 21.7373C84.584 19.3642 85.9575 17.825 88.0858 17.825C90.1791 17.825 91.4954 19.263 91.4954 21.557V22.0891H86.1517V22.1829C86.1018 22.7462 86.2963 23.3038 86.6857 23.7138C87.0751 24.1238 87.6219 24.3468 88.187 24.326C88.9407 24.3966 89.6512 23.9638 89.9343 23.2618L91.4089 23.2618ZM89.9417 21.0037H86.1591C86.1578 20.4929 86.3606 20.0027 86.7224 19.6421C87.0842 19.2815 87.5751 19.0804 88.0859 19.0834C88.5927 19.0725 89.0811 19.2736 89.4334 19.6381C89.7856 20.0026 89.9699 20.4975 89.9417 21.0037Z"
                                                            fill="white"
                                                          />
                                                          <path
                                                            fill-rule="evenodd"
                                                            clip-rule="evenodd"
                                                            d="M49.3834 7.05078H50.126V12.2827H49.3834V7.05078ZM33.3702 7.97994C32.9157 7.49999 32.27 7.24915 31.6107 7.29647H29.8096V12.2828H31.6107C33.096 12.2828 33.9569 11.3671 33.9569 9.7741C34.0401 9.11835 33.8247 8.45989 33.3702 7.97994ZM31.5239 11.5777H30.5838V8.0012H31.5239C31.9926 7.97519 32.4483 8.15999 32.7665 8.50505C33.0847 8.85011 33.2321 9.31932 33.1683 9.78435C33.2371 10.2513 33.0918 10.7244 32.7728 11.0723C32.4538 11.4202 31.9951 11.6059 31.5239 11.5777ZM34.8314 10.3997C34.7669 9.72523 35.0903 9.07248 35.6661 8.71522C36.2418 8.35795 36.9703 8.35795 37.546 8.71522C38.1217 9.07248 38.4451 9.72523 38.3806 10.3997C38.4463 11.0749 38.1232 11.7289 37.547 12.087C36.9708 12.445 36.2413 12.445 35.665 12.087C35.0888 11.7289 34.7657 11.0749 34.8314 10.3997ZM36.608 9.10694C37.2511 9.10694 37.6175 9.58394 37.6175 10.3996H37.6175C37.6175 11.2186 37.2511 11.6951 36.608 11.6952C35.9625 11.6952 35.5994 11.2218 35.5994 10.3996C35.5994 9.58394 35.9625 9.10694 36.608 9.10694ZM43.0984 12.2828H42.328L41.5503 9.51132H41.4915L40.7171 12.2828H39.954L38.9168 8.51978H39.67L40.3441 11.3911H40.3996L41.1732 8.51978H41.8857L42.6593 11.3911H42.7181L43.3889 8.51978H44.1316L43.0984 12.2828ZM45.7195 8.51972H45.0047L45.0047 12.2827H45.7473V10.0813C45.7206 9.83088 45.8047 9.5813 45.9774 9.39801C46.1501 9.21472 46.3942 9.11599 46.6458 9.12771C47.195 9.12771 47.4578 9.42844 47.4578 10.0332V12.2827H48.2005V9.84667C48.2549 9.47875 48.1391 9.10603 47.8857 8.83375C47.6323 8.56148 47.2689 8.4192 46.898 8.44709C46.419 8.40815 45.968 8.67738 45.775 9.11751H45.7195V8.51972ZM51.9932 8.71503C51.4174 9.0723 51.0939 9.72507 51.1584 10.3996C51.0927 11.0748 51.4159 11.7288 51.9922 12.0869C52.5684 12.4449 53.2979 12.4449 53.8742 12.0869C54.4505 11.7288 54.7736 11.0748 54.708 10.3996C54.7724 9.72507 54.449 9.0723 53.8732 8.71503C53.2974 8.35777 52.5689 8.35777 51.9932 8.71503ZM53.9437 10.3996C53.9437 9.58392 53.5773 9.10692 52.9342 9.10692C52.2887 9.10692 51.9256 9.58392 51.9256 10.3996C51.9256 11.2218 52.2887 11.6951 52.9342 11.6951C53.5773 11.6951 53.9437 11.2185 53.9437 10.3996ZM56.8895 10.0952C55.9942 10.1507 55.4899 10.5412 55.4899 11.2185C55.4912 11.5397 55.6282 11.8454 55.8672 12.0599C56.1062 12.2745 56.4248 12.3779 56.7442 12.3447C57.1999 12.3658 57.6317 12.1401 57.8745 11.7539H57.9333V12.2827H58.6482V9.71164C58.6482 8.91677 58.1161 8.44712 57.1727 8.44712C56.319 8.44712 55.7111 8.86169 55.6352 9.50803H56.3541C56.4366 9.24198 56.7238 9.08978 57.1384 9.08978C57.646 9.08978 57.9088 9.3142 57.9088 9.71164V10.0364L56.8895 10.0952ZM57.9083 10.8971V10.5825L56.9893 10.6413C56.4711 10.676 56.2361 10.8522 56.2361 11.184C56.2361 11.5226 56.5299 11.7197 56.9339 11.7197C57.173 11.7439 57.4118 11.6701 57.5955 11.515C57.7791 11.3599 57.892 11.137 57.9083 10.8971ZM61.1858 8.45732C60.235 8.45732 59.6238 9.21057 59.6238 10.3996C59.6238 11.5915 60.2285 12.3448 61.1858 12.3447C61.6711 12.3626 62.1263 12.1097 62.3675 11.6882H62.4262V12.2827H63.1379V7.05078H62.3952V9.1175H62.3397C62.1155 8.69244 61.6659 8.4352 61.1858 8.45732ZM60.3913 10.3996C60.3913 11.1977 60.7675 11.678 61.3967 11.678C62.0226 11.678 62.4095 11.1908 62.4095 10.4029C62.4095 9.61861 62.0186 9.12447 61.3967 9.12447C60.7716 9.12447 60.3913 9.60799 60.3913 10.3996H60.3913ZM67.045 8.71522C66.4692 9.07248 66.1458 9.72523 66.2103 10.3997C66.1446 11.0749 66.4677 11.7289 67.0439 12.087C67.6202 12.445 68.3497 12.445 68.9259 12.087C69.5021 11.7289 69.8253 11.0749 69.7595 10.3997C69.824 9.72523 69.5006 9.07248 68.9249 8.71522C68.3492 8.35795 67.6207 8.35795 67.045 8.71522ZM68.9964 10.3996C68.9964 9.58394 68.63 9.10694 67.9869 9.10694C67.3414 9.10694 66.9783 9.58394 66.9783 10.3996C66.9783 11.2218 67.3414 11.6952 67.9869 11.6952C68.63 11.6951 68.9964 11.2186 68.9964 10.3996ZM71.4715 8.51972H70.7566V12.2827H71.4992V10.0813C71.4726 9.83088 71.5566 9.5813 71.7294 9.39801C71.9021 9.21472 72.1462 9.11599 72.3978 9.12771C72.947 9.12771 73.2098 9.42844 73.2098 10.0332V12.2827H73.9524V9.84667C74.0068 9.47875 73.891 9.10603 73.6376 8.83375C73.3843 8.56148 73.0208 8.4192 72.6499 8.44709C72.1709 8.40815 71.72 8.67738 71.527 9.11751H71.4715V8.51972ZM78.1482 7.58277V8.53677H78.9635V9.16231H78.1482V11.0972C78.1482 11.4914 78.3106 11.664 78.6803 11.664C78.775 11.6637 78.8695 11.658 78.9635 11.6469V12.2655C78.8301 12.2893 78.695 12.302 78.5595 12.3034C77.7336 12.3034 77.4048 12.0129 77.4048 11.2874V9.16227H76.8074V8.53674H77.4048V7.58277H78.1482ZM80.7142 7.05078H79.9781L79.9782 12.2827H80.7208V10.085C80.6978 9.82677 80.79 9.57158 80.9727 9.38765C81.1554 9.20372 81.4099 9.10979 81.6683 9.13098C82.193 9.13098 82.4729 9.43538 82.4729 10.0364V12.2827H83.2163V9.85323C83.2655 9.48719 83.1489 9.11829 82.8983 8.847C82.6477 8.57572 82.2891 8.43031 81.9204 8.45037C81.4341 8.41091 80.9752 8.68048 80.773 9.12446H80.7142V7.05078ZM87.5466 11.2667C87.3384 11.9765 86.6515 12.4351 85.9161 12.3553C85.4143 12.3686 84.9321 12.1606 84.5974 11.7866C84.2626 11.4126 84.1092 10.9103 84.1778 10.4131C84.111 9.91446 84.2639 9.41139 84.5967 9.03421C84.9296 8.65702 85.4098 8.44282 85.9128 8.44711C86.9599 8.44711 87.5915 9.16245 87.5915 10.3441V10.6032H84.9343V10.6448C84.9108 10.9241 85.0062 11.2003 85.197 11.4055C85.3878 11.6107 85.6563 11.726 85.9365 11.7229C86.2998 11.7665 86.6535 11.5863 86.8317 11.2667L87.5466 11.2667ZM86.8349 10.054H84.9342C84.9309 9.7945 85.0324 9.54466 85.2159 9.36112C85.3994 9.17758 85.6492 9.07591 85.9086 9.07917C86.1647 9.0733 86.4112 9.17582 86.5876 9.36146C86.764 9.5471 86.8538 9.7986 86.8349 10.054Z"
                                                            fill="white"
                                                          />
                                                          <path
                                                            fill-rule="evenodd"
                                                            clip-rule="evenodd"
                                                            d="M19.3474 7.28906C19.4283 8.34555 19.0942 9.39196 18.416 10.2061C17.7581 11.0244 16.7611 11.4958 15.7111 11.485C15.6444 10.4589 15.9882 9.44837 16.6669 8.67595C17.3542 7.89307 18.3113 7.39788 19.3474 7.28906ZM22.6681 13.4967C21.4603 14.2393 20.717 15.5488 20.6988 16.9665C20.7005 18.5704 21.6611 20.0179 23.1383 20.6428C22.8542 21.5657 22.4259 22.4379 21.8694 23.2271C21.122 24.3451 20.3383 25.4374 19.0945 25.4576C18.503 25.4713 18.1037 25.3012 17.6876 25.1239C17.2536 24.939 16.8014 24.7464 16.0936 24.7464C15.3431 24.7464 14.8706 24.9452 14.4149 25.137C14.0211 25.3028 13.6398 25.4632 13.1024 25.4855C11.9179 25.5294 11.0126 24.2922 10.2381 23.1846C8.68989 20.9228 7.48435 16.8104 9.10054 14.0122C9.85948 12.6484 11.2785 11.783 12.8384 11.7326C13.5103 11.7188 14.1549 11.9777 14.7201 12.2048C15.1523 12.3784 15.5381 12.5333 15.854 12.5333C16.1316 12.5333 16.5066 12.3845 16.9437 12.211C17.6321 11.9378 18.4744 11.6035 19.3328 11.6936C20.6666 11.7353 21.9027 12.4036 22.6681 13.4967Z"
                                                            fill="white"
                                                          />
                                                        </svg>
                                                      </a>
                                                    </span>
                                                    <span>
                                                      <a href="" style="text-decoration: none">
                                                        <svg
                                                          width="100"
                                                          height="34"
                                                          viewBox="0 0 100 34"
                                                          fill="none"
                                                          xmlns="http://www.w3.org/2000/svg"
                                                        >
                                                          <rect
                                                            y="0.898438"
                                                            width="100"
                                                            height="33"
                                                            rx="5"
                                                            fill="black"
                                                          />
                                                          <path
                                                            fill-rule="evenodd"
                                                            clip-rule="evenodd"
                                                            d="M3.7037 0.898438H96.2963C98.3418 0.898438 100 2.55664 100 4.60214V26.8244C100 28.8699 98.3418 30.5281 96.2963 30.5281H3.7037C1.6582 30.5281 0 28.8699 0 26.8244V4.60214C0 2.55664 1.6582 0.898438 3.7037 0.898438ZM99.4007 4.60159C99.4007 2.88337 98.0079 1.49048 96.2896 1.49048H3.69705C1.97883 1.49048 0.585938 2.88337 0.585938 4.60159V26.8238C0.585938 28.542 1.97883 29.9349 3.69705 29.9349H96.2896C98.0079 29.9349 99.4007 28.542 99.4007 26.8238V4.60159Z"
                                                            fill="#A6A6A6"
                                                          />
                                                          <path
                                                            fill-rule="evenodd"
                                                            clip-rule="evenodd"
                                                            d="M35.3601 17.9809V19.3142H38.5601C38.51 19.9422 38.2489 20.5348 37.8194 20.9957C37.172 21.6551 36.2761 22.0103 35.3527 21.9735C33.3891 21.9735 31.7972 20.3816 31.7972 18.4179C31.7972 16.4542 33.3891 14.8624 35.3527 14.8624C36.2548 14.8477 37.1259 15.1913 37.775 15.8179L38.7157 14.8772C37.8305 13.9808 36.6199 13.4811 35.3601 13.492C33.5668 13.4222 31.879 14.3392 30.9615 15.8817C30.0441 17.4242 30.0441 19.345 30.9615 20.8875C31.879 22.43 33.5668 23.347 35.3601 23.2772C36.6429 23.3352 37.8881 22.8355 38.775 21.9068C39.551 21.0542 39.9654 19.9333 39.9305 18.7809C39.9329 18.5001 39.9081 18.2198 39.8564 17.9439L35.3601 17.9809ZM43.5704 17.0106C41.8327 17.0147 40.427 18.426 40.4297 20.1637C40.4324 21.9014 41.8426 23.3082 43.5803 23.3069C45.318 23.3055 46.726 21.8964 46.726 20.1587C46.7382 19.3175 46.4084 18.5074 45.8121 17.9139C45.2158 17.3204 44.4042 16.9944 43.563 17.0106H43.5704ZM47.3633 20.1686C47.3579 18.4318 48.7599 17.0188 50.4966 17.0106C51.3378 16.9944 52.1494 17.3204 52.7457 17.9139C53.342 18.5074 53.6718 19.3175 53.6596 20.1587C53.6596 21.8955 52.2531 23.3041 50.5163 23.3068C48.7796 23.3096 47.3687 21.9053 47.3633 20.1686ZM48.6242 20.9871C48.9625 21.6951 49.6992 22.1245 50.482 22.0697C50.9733 22.0583 51.4392 21.8489 51.7739 21.4891C52.1087 21.1293 52.2839 20.6495 52.2598 20.1586C52.2578 19.3739 51.7765 18.6701 51.0458 18.3838C50.3152 18.0976 49.4838 18.287 48.9494 18.8616C48.4149 19.4361 48.286 20.279 48.6242 20.9871ZM41.6931 20.9937C42.0337 21.7014 42.7726 22.1285 43.5558 22.0707H43.5484C44.0397 22.0593 44.5056 21.8499 44.8403 21.4901C45.1751 21.1303 45.3503 20.6505 45.3262 20.1596C45.324 19.3743 44.8417 18.6702 44.1101 18.3846C43.3786 18.0989 42.5468 18.2899 42.0131 18.866C41.4794 19.4422 41.3524 20.2861 41.6931 20.9937ZM66.25 17.0115C67.4781 17.0568 68.5508 17.8554 68.9463 19.0189L69.1019 19.3523L64.8871 21.093C65.1512 21.6947 65.7564 22.0736 66.413 22.0486C67.0469 22.0502 67.635 21.7182 67.9611 21.1745L69.0352 21.9152C68.4505 22.7904 67.4655 23.3135 66.413 23.3078C65.5764 23.3201 64.7707 22.9916 64.1812 22.3979C63.5916 21.8042 63.2689 20.9962 63.287 20.1597C63.2359 19.3423 63.5253 18.5402 64.0866 17.9438C64.6479 17.3474 65.431 17.0099 66.25 17.0115ZM64.6521 20.0704C64.6218 19.6076 64.779 19.152 65.0884 18.8064C65.3978 18.4608 65.8332 18.2543 66.2966 18.2334C66.785 18.2012 67.2455 18.4635 67.4669 18.9L64.6521 20.0704ZM62.6157 23.1214H61.2305V13.8621H62.6157V23.1214ZM58.9619 17.7141H58.91C58.4878 17.242 57.8841 16.9726 57.2508 16.9734C55.5694 17.0549 54.248 18.4419 54.248 20.1252C54.248 21.8086 55.5694 23.1956 57.2508 23.2771C57.8862 23.2878 58.4937 23.0166 58.91 22.5364H58.9545V22.9882C58.9545 24.1956 58.31 24.8401 57.273 24.8401C56.5665 24.8235 55.9402 24.3816 55.6878 23.7215L54.4804 24.2252C54.9453 25.3562 56.0502 26.0918 57.273 26.0845C58.8952 26.0845 60.2359 25.1289 60.2359 22.803V17.1956H58.9619V17.7141ZM57.363 22.0695L57.3653 22.0697H57.3579L57.363 22.0695ZM58.6112 21.4755C58.2933 21.8352 57.8425 22.0497 57.363 22.0695C56.3737 21.9811 55.6152 21.1521 55.6152 20.1586C55.6152 19.1643 56.3749 18.3348 57.3653 18.2475C57.8439 18.2721 58.2924 18.4888 58.6089 18.8486C58.9255 19.2084 59.0834 19.6808 59.0468 20.1586C59.0876 20.6386 58.9303 21.1145 58.6112 21.4755ZM75.4303 13.8635H72.1191V23.1227H73.5043V19.6116H75.4377C76.5137 19.6889 77.5428 19.1586 78.1044 18.2375C78.666 17.3164 78.666 16.1587 78.1044 15.2375C77.5428 14.3164 76.5137 13.7861 75.4377 13.8635H75.4303ZM73.4863 18.307H75.4197L75.4493 18.3293C76.3268 18.3293 77.0382 17.6179 77.0382 16.7404C77.0382 15.8629 76.3268 15.1515 75.4493 15.1515H73.4863V18.307ZM83.9551 16.9735C82.9245 16.9119 81.9556 17.4676 81.4884 18.3883L82.718 18.8994C82.9657 18.4428 83.4596 18.1756 83.9773 18.2179C84.3325 18.1764 84.6895 18.2794 84.968 18.5036C85.2466 18.7279 85.4234 19.0546 85.4588 19.4105V19.5068C85.0139 19.2712 84.5177 19.1491 84.0143 19.1512C82.6884 19.1512 81.3477 19.892 81.3477 21.2327C81.3741 21.8103 81.633 22.3526 82.0655 22.7363C82.498 23.1199 83.0674 23.3124 83.644 23.2697C84.3545 23.3208 85.0362 22.9799 85.4217 22.3809H85.4662V23.1216H86.7995V19.5586C86.7995 17.9364 85.5699 16.9957 83.9921 16.9957L83.9551 16.9735ZM82.7148 21.2629C82.7148 21.8184 83.3445 22.048 83.7963 22.048L83.8334 22.0703C84.6678 22.0499 85.3598 21.418 85.4556 20.5888C85.0712 20.3742 84.6364 20.2668 84.1963 20.2777C83.5 20.2777 82.7148 20.5221 82.7148 21.2629ZM91.6696 17.1962L90.0844 21.211H90.04L88.3955 17.1962H86.9141L89.3807 22.811L87.9733 25.9295H89.4178L93.2104 17.1962H91.6696ZM80.5844 23.1214H79.1992V13.8621H80.5844V23.1214Z"
                                                            fill="white"
                                                          />
                                                          <path
                                                            fill-rule="evenodd"
                                                            clip-rule="evenodd"
                                                            d="M51.2902 6.65506C50.4163 7.58007 50.4163 9.02634 51.2902 9.95135C52.1917 10.8478 53.6479 10.8478 54.5494 9.95135C55.427 9.02783 55.427 7.57858 54.5494 6.65506C54.1183 6.22079 53.5317 5.97656 52.9198 5.97656C52.3079 5.97656 51.7213 6.22079 51.2902 6.65506ZM34.56 9.96535C34.9402 9.56841 35.141 9.03293 35.1156 8.48387C35.1156 8.3721 35.1057 8.26055 35.0859 8.15054H32.9304V8.68387H34.5378C34.5268 9.0198 34.3953 9.34056 34.1674 9.58757C33.6597 10.0783 32.9066 10.2155 32.2585 9.93517C31.6104 9.65486 31.1945 9.01216 31.2044 8.30609C31.1883 7.83885 31.3633 7.3852 31.6889 7.04975C32.0146 6.71431 32.4629 6.52602 32.9304 6.52831C33.3955 6.5029 33.8454 6.6977 34.1452 7.05424L34.5378 6.66165C34.3481 6.44354 34.1095 6.27347 33.8415 6.16535C33.5536 6.04214 33.2435 5.97912 32.9304 5.98017C32.316 5.96647 31.7234 6.20782 31.2933 6.64683C30.6315 7.313 30.4335 8.31099 30.7909 9.17936C31.1483 10.0477 31.9914 10.6173 32.9304 10.6246C33.5426 10.6464 34.1352 10.4067 34.56 9.96535ZM36.5301 6.63305H38.5301V6.0849H35.9375V10.5293H38.5301V9.9812H36.5301V8.57379H38.3523V8.04046H36.5301V6.63305ZM40.9261 10.5293H40.3558V6.63305H39.1113V6.0849H42.2002V6.63305H40.9261V10.5293ZM44.3848 6.0849V10.5293H44.9551V6.0849H44.3848ZM47.5179 10.5293H46.9476V6.63305H45.7031V6.0849H48.755V6.63305H47.5179V10.5293ZM51.7125 9.58183C52.3801 10.2467 53.4596 10.2467 54.1273 9.58183C54.783 8.85899 54.783 7.75652 54.1273 7.03368C53.4596 6.36882 52.3801 6.36882 51.7125 7.03368C51.0568 7.75652 51.0568 8.85899 51.7125 9.58183ZM56.0059 6.0849V10.5293H56.5836V6.90712L58.8429 10.5293H59.4355V6.0849H58.8651V9.54416L56.7022 6.0849H56.0059Z"
                                                            fill="white"
                                                          />
                                                          <path
                                                            d="M51.2902 9.95135L51.2174 10.0201L51.2197 10.0223L51.2902 9.95135ZM51.2902 6.65506L51.2192 6.58458L51.2175 6.58638L51.2902 6.65506ZM54.5494 9.95135L54.62 10.0223L54.6219 10.0202L54.5494 9.95135ZM54.5494 6.65506L54.6219 6.58615L54.6204 6.5846L54.5494 6.65506ZM35.1156 8.48387L35.0154 8.48383L35.0157 8.4885L35.1156 8.48387ZM34.56 9.96535L34.632 10.0347L34.6322 10.0345L34.56 9.96535ZM35.0859 8.15054L35.1844 8.13286L35.1696 8.05054H35.0859V8.15054ZM32.9304 8.15054V8.05054H32.8304V8.15054H32.9304ZM32.9304 8.68387H32.8304V8.78387H32.9304V8.68387ZM34.5378 8.68387L34.6377 8.68714L34.6411 8.58387H34.5378V8.68387ZM34.1674 9.58757L34.237 9.65959L34.2409 9.65539L34.1674 9.58757ZM32.2585 9.93517L32.2188 10.027L32.2585 9.93517ZM31.2044 8.30609L31.3046 8.3075L31.3044 8.30264L31.2044 8.30609ZM31.6889 7.04975L31.6172 6.98009L31.6889 7.04975ZM32.9304 6.52831L32.9299 6.62849L32.9358 6.62817L32.9304 6.52831ZM34.1452 7.05424L34.0686 7.11859L34.1388 7.20205L34.2159 7.12495L34.1452 7.05424ZM34.5378 6.66165L34.6085 6.73236L34.6744 6.66641L34.6132 6.59603L34.5378 6.66165ZM33.8415 6.16535L33.8021 6.25731L33.8041 6.25809L33.8415 6.16535ZM32.9304 5.98017L32.9281 6.08017L32.9307 6.08017L32.9304 5.98017ZM31.2933 6.64683L31.3643 6.71731L31.3648 6.71681L31.2933 6.64683ZM30.7909 9.17936L30.6984 9.21742H30.6984L30.7909 9.17936ZM32.9304 10.6246L32.9339 10.5246L32.9312 10.5246L32.9304 10.6246ZM38.5301 6.63305V6.73305H38.6301V6.63305H38.5301ZM36.5301 6.63305V6.53305H36.4301V6.63305H36.5301ZM38.5301 6.0849H38.6301V5.9849H38.5301V6.0849ZM35.9375 6.0849V5.9849H35.8375V6.0849H35.9375ZM35.9375 10.5293H35.8375V10.6293H35.9375V10.5293ZM38.5301 10.5293V10.6293H38.6301V10.5293H38.5301ZM38.5301 9.9812H38.6301V9.8812H38.5301V9.9812ZM36.5301 9.9812H36.4301V10.0812H36.5301V9.9812ZM36.5301 8.57379V8.47379H36.4301V8.57379H36.5301ZM38.3523 8.57379V8.67379H38.4523V8.57379H38.3523ZM38.3523 8.04046H38.4523V7.94046H38.3523V8.04046ZM36.5301 8.04046H36.4301V8.14046H36.5301V8.04046ZM40.3558 10.5293H40.2558V10.6293H40.3558V10.5293ZM40.9261 10.5293V10.6293H41.0261V10.5293H40.9261ZM40.3558 6.63305H40.4558V6.53305H40.3558V6.63305ZM39.1113 6.63305H39.0113V6.73305H39.1113V6.63305ZM39.1113 6.0849V5.9849H39.0113V6.0849H39.1113ZM42.2002 6.0849H42.3002V5.9849H42.2002V6.0849ZM42.2002 6.63305V6.73305H42.3002V6.63305H42.2002ZM40.9261 6.63305V6.53305H40.8261V6.63305H40.9261ZM44.3848 10.5293H44.2848V10.6293H44.3848V10.5293ZM44.3848 6.0849V5.9849H44.2848V6.0849H44.3848ZM44.9551 10.5293V10.6293H45.0551V10.5293H44.9551ZM44.9551 6.0849H45.0551V5.9849H44.9551V6.0849ZM46.9476 10.5293H46.8476V10.6293H46.9476V10.5293ZM47.5179 10.5293V10.6293H47.6179V10.5293H47.5179ZM46.9476 6.63305H47.0476V6.53305H46.9476V6.63305ZM45.7031 6.63305H45.6031V6.73305H45.7031V6.63305ZM45.7031 6.0849V5.9849H45.6031V6.0849H45.7031ZM48.755 6.0849H48.855V5.9849H48.755V6.0849ZM48.755 6.63305V6.73305H48.855V6.63305H48.755ZM47.5179 6.63305V6.53305H47.4179V6.63305H47.5179ZM54.1273 9.58183L54.1979 9.65278L54.2014 9.64902L54.1273 9.58183ZM51.7125 9.58183L51.6383 9.6491L51.6419 9.65269L51.7125 9.58183ZM54.1273 7.03368L54.2015 6.96641L54.1979 6.96282L54.1273 7.03368ZM51.7125 7.03368L51.6418 6.96273L51.6384 6.96649L51.7125 7.03368ZM56.0059 10.5293H55.9059V10.6293H56.0059V10.5293ZM56.0059 6.0849V5.9849H55.9059V6.0849H56.0059ZM56.5836 10.5293V10.6293H56.6836V10.5293H56.5836ZM56.5836 6.90712L56.6685 6.8542L56.4836 6.55784V6.90712H56.5836ZM58.8429 10.5293L58.758 10.5823L58.7874 10.6293H58.8429V10.5293ZM59.4355 10.5293V10.6293H59.5355V10.5293H59.4355ZM59.4355 6.0849H59.5355V5.9849H59.4355V6.0849ZM58.8651 6.0849V5.9849H58.7651V6.0849H58.8651ZM58.8651 9.54416L58.7803 9.59718L58.9651 9.89271V9.54416H58.8651ZM56.7022 6.0849L56.7869 6.03188L56.7576 5.9849H56.7022V6.0849ZM51.3629 9.88268C50.5254 8.99621 50.5254 7.6102 51.3629 6.72373L51.2175 6.58638C50.3072 7.54994 50.3072 9.05647 51.2175 10.02L51.3629 9.88268ZM54.4789 9.88044C53.6164 10.7381 52.2232 10.7381 51.3607 9.88044L51.2197 10.0223C52.1602 10.9575 53.6794 10.9575 54.6199 10.0223L54.4789 9.88044ZM54.4769 6.72394C55.3179 7.60887 55.3179 8.99754 54.4769 9.88247L54.6219 10.0202C55.5362 9.05811 55.5362 7.5483 54.6219 6.58617L54.4769 6.72394ZM52.9198 6.07656C53.5051 6.07656 54.0661 6.31016 54.4785 6.72551L54.6204 6.5846C54.1705 6.13143 53.5584 5.87656 52.9198 5.87656V6.07656ZM51.3611 6.72551C51.7735 6.31016 52.3345 6.07656 52.9198 6.07656V5.87656C52.2812 5.87656 51.6691 6.13143 51.2192 6.5846L51.3611 6.72551ZM35.0157 8.4885C35.0398 9.01021 34.849 9.51901 34.4878 9.89618L34.6322 10.0345C35.0314 9.61781 35.2422 9.05565 35.2155 8.47924L35.0157 8.4885ZM34.9875 8.16821C35.0062 8.27238 35.0156 8.378 35.0156 8.48383L35.2156 8.48391C35.2156 8.3662 35.2052 8.24872 35.1844 8.13286L34.9875 8.16821ZM32.9304 8.25054H35.0859V8.05054H32.9304V8.25054ZM33.0304 8.68387V8.15054H32.8304V8.68387H33.0304ZM34.5378 8.58387H32.9304V8.78387H34.5378V8.58387ZM34.2409 9.65539C34.4851 9.39074 34.626 9.04706 34.6377 8.68714L34.4378 8.6806C34.4276 8.99254 34.3056 9.29039 34.0939 9.51976L34.2409 9.65539ZM32.2188 10.027C32.904 10.3233 33.7002 10.1783 34.2369 9.65947L34.0979 9.51567C33.6192 9.97837 32.9092 10.1077 32.2982 9.84339L32.2188 10.027ZM31.1045 8.30469C31.094 9.05114 31.5336 9.73061 32.2188 10.027L32.2982 9.84339C31.6871 9.57911 31.2951 8.97318 31.3044 8.3075L31.1045 8.30469ZM31.6172 6.98009C31.2726 7.33506 31.0874 7.81511 31.1045 8.30954L31.3044 8.30264C31.2892 7.86259 31.454 7.43533 31.7607 7.11941L31.6172 6.98009ZM32.9309 6.42832C32.4361 6.42589 31.9618 6.62513 31.6172 6.98009L31.7607 7.11941C32.0674 6.80348 32.4896 6.62615 32.9299 6.62831L32.9309 6.42832ZM34.2217 6.98989C33.9018 6.60929 33.4214 6.40133 32.9249 6.42846L32.9358 6.62817C33.3695 6.60447 33.7891 6.78612 34.0686 7.11859L34.2217 6.98989ZM34.4671 6.59094L34.0745 6.98353L34.2159 7.12495L34.6085 6.73236L34.4671 6.59094ZM33.8041 6.25809C34.0575 6.3603 34.283 6.52108 34.4623 6.72726L34.6132 6.59603C34.4132 6.36601 34.1616 6.18663 33.8789 6.07261L33.8041 6.25809ZM32.9307 6.08017C33.2302 6.07917 33.5268 6.13944 33.8021 6.25729L33.8808 6.07342C33.5804 5.94484 33.2568 5.87908 32.93 5.88017L32.9307 6.08017ZM31.3648 6.71681C31.7754 6.29755 32.3414 6.06707 32.9281 6.08014L32.9326 5.88019C32.2906 5.86588 31.6713 6.11808 31.2219 6.57686L31.3648 6.71681ZM30.8834 9.1413C30.5413 8.31015 30.7308 7.35493 31.3643 6.71731L31.2224 6.57635C30.5322 7.27107 30.3257 8.31183 30.6984 9.21742L30.8834 9.1413ZM32.9312 10.5246C32.0324 10.5176 31.2254 9.97246 30.8834 9.1413L30.6984 9.21742C31.0711 10.123 31.9503 10.7169 32.9296 10.7246L32.9312 10.5246ZM34.488 9.896C34.0828 10.3169 33.5177 10.5455 32.9339 10.5247L32.9268 10.7245C33.5674 10.7474 34.1875 10.4965 34.632 10.0347L34.488 9.896ZM38.5301 6.53305H36.5301V6.73305H38.5301V6.53305ZM38.4301 6.0849V6.63305H38.6301V6.0849H38.4301ZM35.9375 6.1849H38.5301V5.9849H35.9375V6.1849ZM36.0375 10.5293V6.0849H35.8375V10.5293H36.0375ZM38.5301 10.4293H35.9375V10.6293H38.5301V10.4293ZM38.4301 9.9812V10.5293H38.6301V9.9812H38.4301ZM36.5301 10.0812H38.5301V9.8812H36.5301V10.0812ZM36.4301 8.57379V9.9812H36.6301V8.57379H36.4301ZM38.3523 8.47379H36.5301V8.67379H38.3523V8.47379ZM38.2523 8.04046V8.57379H38.4523V8.04046H38.2523ZM36.5301 8.14046H38.3523V7.94046H36.5301V8.14046ZM36.4301 6.63305V8.04046H36.6301V6.63305H36.4301ZM40.3558 10.6293H40.9261V10.4293H40.3558V10.6293ZM40.2558 6.63305V10.5293H40.4558V6.63305H40.2558ZM39.1113 6.73305H40.3558V6.53305H39.1113V6.73305ZM39.0113 6.0849V6.63305H39.2113V6.0849H39.0113ZM42.2002 5.9849H39.1113V6.1849H42.2002V5.9849ZM42.3002 6.63305V6.0849H42.1002V6.63305H42.3002ZM40.9261 6.73305H42.2002V6.53305H40.9261V6.73305ZM41.0261 10.5293V6.63305H40.8261V10.5293H41.0261ZM44.4848 10.5293V6.0849H44.2848V10.5293H44.4848ZM44.9551 10.4293H44.3848V10.6293H44.9551V10.4293ZM44.8551 6.0849V10.5293H45.0551V6.0849H44.8551ZM44.3848 6.1849H44.9551V5.9849H44.3848V6.1849ZM46.9476 10.6293H47.5179V10.4293H46.9476V10.6293ZM46.8476 6.63305V10.5293H47.0476V6.63305H46.8476ZM45.7031 6.73305H46.9476V6.53305H45.7031V6.73305ZM45.6031 6.0849V6.63305H45.8031V6.0849H45.6031ZM48.755 5.9849H45.7031V6.1849H48.755V5.9849ZM48.855 6.63305V6.0849H48.655V6.63305H48.855ZM47.5179 6.73305H48.755V6.53305H47.5179V6.73305ZM47.6179 10.5293V6.63305H47.4179V10.5293H47.6179ZM54.0567 9.51097C53.4281 10.137 52.4117 10.137 51.783 9.51097L51.6419 9.65269C52.3486 10.3564 53.4912 10.3564 54.1979 9.65269L54.0567 9.51097ZM54.0532 7.10087C54.6744 7.78559 54.6744 8.82992 54.0532 9.51464L54.2014 9.64902C54.8916 8.88806 54.8916 7.72745 54.2014 6.96649L54.0532 7.10087ZM51.783 7.10454C52.4117 6.47853 53.4281 6.47853 54.0567 7.10454L54.1979 6.96282C53.4912 6.2591 52.3486 6.2591 51.6419 6.96282L51.783 7.10454ZM51.7865 9.51464C51.1654 8.82992 51.1654 7.78559 51.7865 7.10087L51.6384 6.96649C50.9481 7.72745 50.9481 8.88806 51.6384 9.64902L51.7865 9.51464ZM56.1059 10.5293V6.0849H55.9059V10.5293H56.1059ZM56.5836 10.4293H56.0059V10.6293H56.5836V10.4293ZM56.4836 6.90712V10.5293H56.6836V6.90712H56.4836ZM58.9277 10.4764L56.6685 6.8542L56.4988 6.96004L58.758 10.5823L58.9277 10.4764ZM59.4355 10.4293H58.8429V10.6293H59.4355V10.4293ZM59.3355 6.0849V10.5293H59.5355V6.0849H59.3355ZM58.8651 6.1849H59.4355V5.9849H58.8651V6.1849ZM58.9651 9.54416V6.0849H58.7651V9.54416H58.9651ZM56.6174 6.13792L58.7803 9.59718L58.9499 9.49114L56.7869 6.03188L56.6174 6.13792ZM56.0059 6.1849H56.7022V5.9849H56.0059V6.1849Z"
                                                            fill="white"
                                                          />
                                                          <path
                                                            d="M7.71644 6.48545C7.47501 6.77516 7.35315 7.14603 7.3757 7.52248V23.9077C7.35315 24.2841 7.47501 24.655 7.71644 24.9447L7.76829 24.9966L16.9461 15.8262V15.6114L7.76829 6.43359L7.71644 6.48545Z"
                                                            fill="url(#paint0_linear_2652_4260)"
                                                          />
                                                          <path
                                                            d="M19.9804 18.8837L16.9434 15.8245V15.6096L19.9804 12.543L20.0471 12.58L23.6841 14.6467C24.7211 15.2319 24.7211 16.1948 23.6841 16.7874L20.0619 18.8467L19.9804 18.8837Z"
                                                            fill="url(#paint1_linear_2652_4260)"
                                                          />
                                                          <path
                                                            d="M20.0704 18.8408L16.9445 15.7148L7.71484 24.9445C8.15093 25.3278 8.79812 25.3465 9.25558 24.9889L20.0778 18.8408"
                                                            fill="url(#paint2_linear_2652_4260)"
                                                          />
                                                          <path
                                                            d="M20.0778 12.5879L9.25558 6.43972C8.79812 6.08215 8.15093 6.10082 7.71484 6.48417L16.9519 15.7138L20.0778 12.5879Z"
                                                            fill="url(#paint3_linear_2652_4260)"
                                                          />
                                                          <path
                                                            opacity="0.2"
                                                            d="M19.9815 18.7734L9.24818 24.8845C8.80895 25.2129 8.20593 25.2129 7.7667 24.8845L7.71484 24.9364L7.7667 24.9883C8.2051 25.3191 8.80977 25.3191 9.24818 24.9883L20.0704 18.8401L19.9815 18.7734Z"
                                                            fill="black"
                                                          />
                                                          <path
                                                            opacity="0.12"
                                                            fill-rule="evenodd"
                                                            clip-rule="evenodd"
                                                            d="M20.0195 18.7741L23.7232 16.6778C24.106 16.4889 24.3796 16.1332 24.464 15.7148C24.4258 16.1774 24.1433 16.5842 23.7232 16.7815L20.0862 18.8408L20.0195 18.7741ZM7.37409 23.9477C7.38632 24.2736 7.50593 24.588 7.71644 24.8406L7.76829 24.8998L7.71644 24.9517C7.48208 24.6705 7.36039 24.3128 7.37409 23.9477ZM7.37409 23.9477C7.3745 23.9367 7.37504 23.9257 7.3757 23.9146V23.8035C7.37281 23.8517 7.37229 23.8999 7.37409 23.9477Z"
                                                            fill="black"
                                                          />
                                                          <path
                                                            opacity="0.25"
                                                            d="M9.25453 6.54247L23.6916 14.7499C24.0743 14.9388 24.3479 15.2944 24.4323 15.7128C24.3941 15.2503 24.1116 14.8435 23.6916 14.6462L9.25453 6.43877C8.21749 5.85358 7.37305 6.34247 7.37305 7.52766V7.63877C7.39527 6.44618 8.2249 5.95729 9.25453 6.54247Z"
                                                            fill="white"
                                                          />
                                                          <defs>
                                                            <linearGradient
                                                              id="paint0_linear_2652_4260"
                                                              x1="12.9262"
                                                              y1="-4.69898"
                                                              x2="-1.76963"
                                                              y2="-0.790579"
                                                              gradientUnits="userSpaceOnUse"
                                                            >
                                                              <stop stop-color="#00A0FF" />
                                                              <stop offset="0.01" stop-color="#00A1FF" />
                                                              <stop offset="0.26" stop-color="#00BEFF" />
                                                              <stop offset="0.51" stop-color="#00D2FF" />
                                                              <stop offset="0.76" stop-color="#00DFFF" />
                                                              <stop offset="1" stop-color="#00E3FF" />
                                                            </linearGradient>
                                                            <linearGradient
                                                              id="paint1_linear_2652_4260"
                                                              x1="25.0397"
                                                              y1="8.15755"
                                                              x2="7.12114"
                                                              y2="8.15755"
                                                              gradientUnits="userSpaceOnUse"
                                                            >
                                                              <stop stop-color="#FFE000" />
                                                              <stop offset="0.41" stop-color="#FFBD00" />
                                                              <stop offset="0.78" stop-color="#FFA500" />
                                                              <stop offset="1" stop-color="#FF9C00" />
                                                            </linearGradient>
                                                            <linearGradient
                                                              id="paint2_linear_2652_4260"
                                                              x1="7.44365"
                                                              y1="10.9204"
                                                              x2="-4.36112"
                                                              y2="30.7772"
                                                              gradientUnits="userSpaceOnUse"
                                                            >
                                                              <stop stop-color="#FF3A44" />
                                                              <stop offset="1" stop-color="#C31162" />
                                                            </linearGradient>
                                                            <linearGradient
                                                              id="paint3_linear_2652_4260"
                                                              x1="0.514858"
                                                              y1="3.93306"
                                                              x2="5.78396"
                                                              y2="12.8001"
                                                              gradientUnits="userSpaceOnUse"
                                                            >
                                                              <stop stop-color="#32A071" />
                                                              <stop offset="0.07" stop-color="#2DA771" />
                                                              <stop offset="0.48" stop-color="#15CF74" />
                                                              <stop offset="0.8" stop-color="#06E775" />
                                                              <stop offset="1" stop-color="#00F076" />
                                                            </linearGradient>
                                                          </defs>
                                                        </svg>
                                                      </a>
                                                    </span>
                                                  </td>
                                                </tr>
                                                <tr>
                                                  <td style="text-align: center; padding-top: 10px">
                                                    <span style="margin-right: 10px">
                                                      <a href="" style="text-decoration: none">
                                                        <svg
                                                          width="20"
                                                          height="21"
                                                          viewBox="0 0 20 21"
                                                          fill="none"
                                                          xmlns="http://www.w3.org/2000/svg"
                                                        >
                                                          <rect
                                                            x="0.4"
                                                            y="0.825781"
                                                            width="19.2"
                                                            height="19.2"
                                                            rx="9.6"
                                                            stroke="#2BD9C7"
                                                            stroke-width="0.8"
                                                          />
                                                          <path
                                                            d="M12.7924 10.75L13.1256 8.57828H11.0418V7.16898C11.0418 6.57484 11.3329 5.9957 12.2662 5.9957H13.2135V4.14672C13.2135 4.14672 12.3538 4 11.5319 4C9.8158 4 8.69408 5.04016 8.69408 6.92313V8.57828H6.7865V10.75H8.69408V16H11.0418V10.75H12.7924Z"
                                                            fill="#2BD9C7"
                                                          />
                                                        </svg>
                                                      </a>
                                                    </span>
                                                    <span style="margin-right: 10px">
                                                      <a href="" style="text-decoration: none">
                                                        <svg
                                                          width="20"
                                                          height="21"
                                                          viewBox="0 0 20 21"
                                                          fill="none"
                                                          xmlns="http://www.w3.org/2000/svg"
                                                        >
                                                          <rect
                                                            x="0.4"
                                                            y="0.825781"
                                                            width="19.2"
                                                            height="19.2"
                                                            rx="9.6"
                                                            stroke="#2BD9C7"
                                                            stroke-width="0.8"
                                                          />
                                                          <path
                                                            d="M14.7665 7.55783C14.7741 7.66442 14.7741 7.77104 14.7741 7.87763C14.7741 11.1289 12.2995 14.8751 7.77665 14.8751C6.38324 14.8751 5.08884 14.4715 4 13.771C4.19798 13.7939 4.38831 13.8015 4.59391 13.8015C5.74363 13.8015 6.80202 13.4132 7.6472 12.7507C6.56598 12.7279 5.65989 12.0198 5.3477 11.0451C5.5 11.068 5.65227 11.0832 5.81219 11.0832C6.03299 11.0832 6.25382 11.0527 6.45939 10.9995C5.33249 10.771 4.48729 9.78118 4.48729 8.58575V8.5553C4.81469 8.73805 5.19543 8.85226 5.59895 8.86747C4.93652 8.42584 4.50252 7.67204 4.50252 6.81924C4.50252 6.3624 4.62433 5.94362 4.83754 5.57813C6.0482 7.07052 7.86801 8.04512 9.90859 8.15173C9.87053 7.96899 9.84768 7.77866 9.84768 7.5883C9.84768 6.23295 10.9441 5.12891 12.3071 5.12891C13.0152 5.12891 13.6548 5.42586 14.104 5.90555C14.6598 5.79896 15.1928 5.59337 15.6649 5.31165C15.4822 5.88273 15.0939 6.36242 14.5837 6.66697C15.0786 6.6137 15.5583 6.47661 16 6.28627C15.665 6.77356 15.2462 7.20755 14.7665 7.55783Z"
                                                            fill="#2BD9C7"
                                                          />
                                                        </svg>
                                                      </a>
                                                    </span>
                                                    <span style="margin-right: 10px">
                                                      <a href="" style="text-decoration: none">
                                                        <svg
                                                          width="20"
                                                          height="21"
                                                          viewBox="0 0 20 21"
                                                          fill="none"
                                                          xmlns="http://www.w3.org/2000/svg"
                                                        >
                                                          <rect
                                                            x="0.4"
                                                            y="0.825781"
                                                            width="19.2"
                                                            height="19.2"
                                                            rx="9.6"
                                                            stroke="#2BD9C7"
                                                            stroke-width="0.8"
                                                          />
                                                          <g clip-path="url(#clip0_2652_4311)">
                                                            <path
                                                              d="M10.0024 7.30488C8.51177 7.30488 7.30942 8.50723 7.30942 9.99785C7.30942 11.4885 8.51177 12.6908 10.0024 12.6908C11.493 12.6908 12.6954 11.4885 12.6954 9.99785C12.6954 8.50723 11.493 7.30488 10.0024 7.30488ZM10.0024 11.7486C9.03911 11.7486 8.25161 10.9635 8.25161 9.99785C8.25161 9.03223 9.03677 8.24707 10.0024 8.24707C10.968 8.24707 11.7532 9.03223 11.7532 9.99785C11.7532 10.9635 10.9657 11.7486 10.0024 11.7486ZM13.4336 7.19473C13.4336 7.54395 13.1524 7.82285 12.8055 7.82285C12.4563 7.82285 12.1774 7.5416 12.1774 7.19473C12.1774 6.84785 12.4586 6.5666 12.8055 6.5666C13.1524 6.5666 13.4336 6.84785 13.4336 7.19473ZM15.2172 7.83223C15.1774 6.99082 14.9852 6.24551 14.3688 5.63145C13.7547 5.01738 13.0094 4.8252 12.168 4.78301C11.3008 4.73379 8.70161 4.73379 7.83442 4.78301C6.99536 4.82285 6.25005 5.01504 5.63364 5.6291C5.01724 6.24316 4.82739 6.98848 4.78521 7.82988C4.73599 8.69707 4.73599 11.2963 4.78521 12.1635C4.82505 13.0049 5.01724 13.7502 5.63364 14.3643C6.25005 14.9783 6.99302 15.1705 7.83442 15.2127C8.70161 15.2619 11.3008 15.2619 12.168 15.2127C13.0094 15.1729 13.7547 14.9807 14.3688 14.3643C14.9829 13.7502 15.175 13.0049 15.2172 12.1635C15.2665 11.2963 15.2665 8.69941 15.2172 7.83223ZM14.0969 13.0939C13.9141 13.5533 13.5602 13.9072 13.0985 14.0924C12.4071 14.3666 10.7665 14.3033 10.0024 14.3033C9.23833 14.3033 7.59536 14.3643 6.9063 14.0924C6.44692 13.9096 6.09302 13.5557 5.90786 13.0939C5.63364 12.4025 5.69692 10.7619 5.69692 9.99785C5.69692 9.23379 5.63599 7.59082 5.90786 6.90176C6.09067 6.44238 6.44458 6.08848 6.9063 5.90332C7.5977 5.6291 9.23833 5.69238 10.0024 5.69238C10.7665 5.69238 12.4094 5.63145 13.0985 5.90332C13.5579 6.08613 13.9118 6.44004 14.0969 6.90176C14.3711 7.59316 14.3079 9.23379 14.3079 9.99785C14.3079 10.7619 14.3711 12.4049 14.0969 13.0939Z"
                                                              fill="#2BD9C7"
                                                            />
                                                          </g>
                                                          <defs>
                                                            <clipPath id="clip0_2652_4311">
                                                              <rect
                                                                width="12"
                                                                height="12"
                                                                fill="white"
                                                                transform="translate(4 4)"
                                                              />
                                                            </clipPath>
                                                          </defs>
                                                        </svg>
                                                      </a>
                                                    </span>
                                                    <span style="margin-right: 10px">
                                                      <a href="" style="text-decoration: none">
                                                        <svg
                                                          width="20"
                                                          height="21"
                                                          viewBox="0 0 20 21"
                                                          fill="none"
                                                          xmlns="http://www.w3.org/2000/svg"
                                                        >
                                                          <rect
                                                            x="0.4"
                                                            y="0.825781"
                                                            width="19.2"
                                                            height="19.2"
                                                            rx="9.6"
                                                            stroke="#2BD9C7"
                                                            stroke-width="0.8"
                                                          />
                                                          <path
                                                            d="M7.10031 14.4998H4.92344V7.48961H7.10031V14.4998ZM6.0107 6.53336C5.31461 6.53336 4.75 5.9568 4.75 5.2607C4.75 4.92634 4.88282 4.60568 5.11925 4.36925C5.35568 4.13282 5.67634 4 6.0107 4C6.34506 4 6.66573 4.13282 6.90215 4.36925C7.13858 4.60568 7.27141 4.92634 7.27141 5.2607C7.27141 5.9568 6.70656 6.53336 6.0107 6.53336ZM15.2477 14.4998H13.0755V11.0873C13.0755 10.274 13.0591 9.23102 11.9437 9.23102C10.8119 9.23102 10.6384 10.1146 10.6384 11.0287V14.4998H8.46391V7.48961H10.5517V8.44586H10.5822C10.8728 7.89508 11.5827 7.31383 12.6419 7.31383C14.845 7.31383 15.25 8.76461 15.25 10.649V14.4998H15.2477Z"
                                                            fill="#2BD9C7"
                                                          />
                                                        </svg>
                                                      </a>
                                                    </span>
                                                    <span style="margin-right: 10px">
                                                      <a href="" style="text-decoration: none">
                                                        <svg
                                                          width="20"
                                                          height="21"
                                                          viewBox="0 0 20 21"
                                                          fill="none"
                                                          xmlns="http://www.w3.org/2000/svg"
                                                        >
                                                          <rect
                                                            x="0.4"
                                                            y="0.825781"
                                                            width="19.2"
                                                            height="19.2"
                                                            rx="9.6"
                                                            stroke="#2BD9C7"
                                                            stroke-width="0.8"
                                                          />
                                                          <path
                                                            d="M15.4511 7.25173C15.3203 6.75902 14.9347 6.37098 14.4452 6.23929C13.5579 6 9.99999 6 9.99999 6C9.99999 6 6.44208 6 5.55476 6.23929C5.06524 6.371 4.6797 6.75902 4.54885 7.25173C4.3111 8.14479 4.3111 10.0081 4.3111 10.0081C4.3111 10.0081 4.3111 11.8714 4.54885 12.7644C4.6797 13.2571 5.06524 13.629 5.55476 13.7607C6.44208 14 9.99999 14 9.99999 14C9.99999 14 13.5579 14 14.4452 13.7607C14.9347 13.629 15.3203 13.2571 15.4511 12.7644C15.6889 11.8714 15.6889 10.0081 15.6889 10.0081C15.6889 10.0081 15.6889 8.14479 15.4511 7.25173ZM8.83635 11.6998V8.31635L11.8101 10.0081L8.83635 11.6998Z"
                                                            fill="#2BD9C7"
                                                          />
                                                        </svg>
                                                      </a>
                                                    </span>
                                                  </td>
                                                </tr>
                                                <tr>
                                                  <td style="text-align: center">
                                                    <p style="color: #000000; font-size: 12px; font-weight: bold">
                                                      Copyright @2023 Oxygen. All rights reserved.
                                                    </p>
                                                  </td>
                                                </tr>
                                              </table>
                                            </body>
                                          </html>""";


}
