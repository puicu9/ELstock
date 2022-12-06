import { Component } from "react";
import 'bootstrap/dist/css/bootstrap.min.css';


class NavigationBar extends Component{
  render(){
    return(
      <nav class="navbar-breadcrumb col-xl-12 col-12 d-flex flex-row p-0">
          <div class="navbar-links-wrapper d-flex">
              <div class="nav-link">
                  <a href="/">HOME</a>
              </div>
              <div class="nav-link">
                  <a href="/members/login">로그인</a>
              </div>
              <div class="nav-link">
                  <a href="/stockMarket/home">국내증시</a>
              </div>
              <div class="nav-link">
                  <a href="/ticker/home">관심종목</a>
              </div>
              <div class="nav-link">
                  <a href="javascript:;">매수매도</a>
              </div>
          </div>
          <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">

              <ul class="navbar-nav navbar-nav-right">
                  <li class="nav-item nav-search d-none d-md-block mr-0">
                      <div class="input-group">
                          <input type="text" class="form-control" placeholder="Search..." aria-label="search" aria-describedby="search"></input>
                          <div class="input-group-prepend">
                  <span class="input-group-text" id="search">
                    <i class="typcn typcn-zoom"></i>
                  </span>
                          </div>
                      </div>
                  </li>
              </ul>
          </div>
      </nav>


    );
  }
}

// App은 클래스 이름과 동일해야 합니다.

export default NavigationBar ;