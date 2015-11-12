/*
 * Copyright © 2015 Paul Chang
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the “Software”), to deal in the Software without
 * restriction, including without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
 * BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.paul.volleydemo.entity;

/**
 * Created by Paul Chang on 2015/11/12.
 */
public class WeatherData {


    /**
     * city : 深圳
     * cityid : 101280601
     * temp1 : 27℃
     * temp2 : 21℃
     * weather : 多云
     * img1 : d1.gif
     * img2 : n1.gif
     * ptime : 08:00
     */

    private WeatherinfoEntity weatherinfo;

    public void setWeatherinfo(WeatherinfoEntity weatherinfo) {
        this.weatherinfo = weatherinfo;
    }

    public WeatherinfoEntity getWeatherinfo() {
        return weatherinfo;
    }

    public static class WeatherinfoEntity {
        private String city;
        private String cityid;
        private String temp1;
        private String temp2;
        private String weather;
        private String img1;
        private String img2;
        private String ptime;

        public void setCity(String city) {
            this.city = city;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public void setTemp1(String temp1) {
            this.temp1 = temp1;
        }

        public void setTemp2(String temp2) {
            this.temp2 = temp2;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public void setImg1(String img1) {
            this.img1 = img1;
        }

        public void setImg2(String img2) {
            this.img2 = img2;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public String getCity() {
            return city;
        }

        public String getCityid() {
            return cityid;
        }

        public String getTemp1() {
            return temp1;
        }

        public String getTemp2() {
            return temp2;
        }

        public String getWeather() {
            return weather;
        }

        public String getImg1() {
            return img1;
        }

        public String getImg2() {
            return img2;
        }

        @Override
        public String toString() {
            return
                    "city='" + city + '\'' +
                    "\r\ncityid='" + cityid + '\'' +
                    "\r\ntemp1='" + temp1 + '\'' +
                    "\r\ntemp2='" + temp2 + '\'' +
                    "\r\nweather='" + weather + '\'' +
                    "\r\nimg1='" + img1 + '\'' +
                    "\r\nimg2='" + img2 + '\'' +
                    "\r\nptime='" + ptime + '\'' ;
        }

        public String getPtime() {
            return ptime;
        }
    }

    @Override
    public String toString() {
        return "WeatherData{\r\n"+weatherinfo+"}";
    }
}
