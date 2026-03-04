  ---
  # 公告欄管理系統

  基於 Spring MVC + Hibernate 的 Java Web 應用程式，實作公告 CRUD、分頁、使用者登入驗證，支援 Docker-compose部署。

  ---

  ## 技術棧

  | Layer | Technology | Version |
  |-------|-----------|---------|
  | Frontend | JSP + Bootstrap | 5.3 |
  | Web Framework | Spring MVC | 6.1.14 |
  | ORM | Hibernate (JPA) | 6.5.2 |
  | Connection Pool | HikariCP | 5.1.0 |
  | Database | MySQL | 8.0 |
  | App Server | Tomcat | 10.1 |
  | Java | Amazon Corretto | 17 |
  | Build | Maven | WAR |
  | Password | jBCrypt | 0.4 |
  | Logging | Log4j2 | - |

  ---

  ## 功能

  - 使用者登入 / 登出
  - 公告列表（分頁）
  - 公告新增 / 編輯 / 刪除
  - 全域例外處理
  - 全鏈路 Log 追蹤（TraceId）
  - tabToken 多分頁獨立登入機制

  ---

  ## 系統需求

  | 部署方式 | 需求 |
  |---------|------|
  | Docker | Docker + docker-compose |
  | 本機開發 | JDK 17、Tomcat 10.1、MySQL 8.0、Maven |
  | CentOS 離線 | Rocky Linux 8、Docker |

  ---

  ## 快速啟動

  ### 方式一：Docker（推薦）

  ```bash

  # 1. 啟動全環境
  cd docker
  docker-compose up -d

  ```

  開啟瀏覽器：[http://localhost:8080/announcement-board/login]

  ---

  ## 預設帳號

  | 帳號 | 密碼 |
  |------|------|
  | admin | admin123 |

  ---

  ## 認證機制設計

  ```
  登入成功
    → Server 產生 UUID tabToken
    → 前端存入 sessionStorage（分頁關閉即消失）
    → 每個請求帶上 tabToken
    → AuthInterceptor 驗證

  優點：不同分頁可獨立登入不同帳號
  ```

