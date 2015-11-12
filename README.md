# Volley示例

### 包含四个部分:

1. 简单请求网络数据：使用Volley预定义的`StringRequest`,`ImageRequest`和`JsonRequest`三个类，配合
`Volley.newRequestQueue()`获得的`RequestQueue`的`add()`方法即可进行异步请求.
2. 管理网络请求：在要进行网络请求时可以使用单例模式维护一个`RequestQueue`来进行全局管理，其中可以维护一个`ImageLoader`
来进行图片加载，其中`ImageLoader`在实例化是需要制定内存缓存的实现方式，一般使用`LruCache`实现
3. 自定义请求方式(如GsonRequest)：继承`Request`类，复写其中的抽象方法`parseNetworkResponse()`和`deliverResponse()`方法，具体可以
参考系统预定义的`StringRequest`等类
4. 使用`NetWorkImageView`来异步加载图片进行显示

### 参考网站:
* [Google-Transmitting Network Data Using Volley](http://developer.android.com/training/volley/index.html)
* [郭霖-Android Volley完全解析](http://blog.csdn.net/guolin_blog/article/details/17612763)

### License

    Copyright © 2015 Paul Chang

    Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files 
    (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge,
     publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do 
     so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF 
    MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE 
    FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION 
    WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.