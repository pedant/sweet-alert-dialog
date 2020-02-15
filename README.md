Sweet Alert Dialog
===================
SweetAlert for Android, a beautiful and clever alert dialog

#### This is the most advanced and contemporary fork of the apparently dead project 
**Added:**
- Ability to set custom view
- More convenient interface to bind listeners (like in AlertDialog)
- Third neutral button with own listener, colors, methods and etc.
- Ability to disable buttons
- Ability to set buttons stroke width
- Dark style of dialogs
- Ability to make dialogs without buttons
- Support of HTML tags
- Ability to set text size
- Ability to set buttons color

Some screenshots of the new features:

<img src="https://cloud.githubusercontent.com/assets/10178982/24260517/c6f72da6-0ffc-11e7-9a16-67fea4010a34.jpg" width="30%"/>

<img src="https://user-images.githubusercontent.com/10178982/59605653-eee87d80-9117-11e9-9421-b116536c9388.png" width="30%"/>

#### Known issues:
- [ ] Bug with buttons height if custom view too big and need scrollview
- [ ] Buttons can handle only one line strings

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Sweet%20Alert%20Dialog-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1065)

[中文版](https://github.com/pedant/sweet-alert-dialog/blob/master/README.zh.md)

Inspired by JavaScript [SweetAlert](http://tristanedwards.me/sweetalert)

[Demo Download](https://github.com/F0RIS/sweet-alert-dialog/releases/download/1.5.3/sample-v1.5.3.apk)

## ScreenShot
![image](https://github.com/pedant/sweet-alert-dialog/raw/master/change_type.gif)

## Setup
The simplest way to use SweetAlertDialog is to add the library as aar dependency to your build.

**Maven**

    <dependency>
      <groupId>com.github.f0ris.sweetalert</groupId>
      <artifactId>library</artifactId>
      <version>1.6.2</version>
      <type>aar</type>
    </dependency>

**Gradle**

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation 'com.github.f0ris.sweetalert:library:1.6.2'
    }

## Usage

show material progress

    SweetAlertDialog pDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
    pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
    pDialog.setTitleText("Loading");
    pDialog.setCancelable(false);
    pDialog.show();

![image](https://github.com/pedant/sweet-alert-dialog/raw/master/play_progress.gif)

You can customize progress bar dynamically with materialish-progress methods via **SweetAlertDialog.getProgressHelper()**:
- resetCount()
- isSpinning()
- spin()
- stopSpinning()
- getProgress()
- setProgress(float progress)
- setInstantProgress(float progress)
- getCircleRadius()
- setCircleRadius(int circleRadius)
- getBarWidth()
- setBarWidth(int barWidth)
- getBarColor()
- setBarColor(int barColor)
- getRimWidth()
- setRimWidth(int rimWidth)
- getRimColor()
- setRimColor(int rimColor)
- getSpinSpeed()
- setSpinSpeed(float spinSpeed)

thanks to the project [materialish-progress](https://github.com/pnikosis/materialish-progress) and [@croccio](https://github.com/croccio) participation.

more usages about progress, please see the sample.

A basic message：

    new SweetAlertDialog(this)
        .setTitleText("Here's a message!")
        .show();

A title with a text under：

    new SweetAlertDialog(this)
        .setTitleText("Here's a message!")
        .setContentText("It's pretty, isn't it?")
        .show();

A error message：

    new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
        .setTitleText("Oops...")
        .setContentText("Something went wrong!")
        .show();

A warning message：

    new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
        .setTitleText("Are you sure?")
        .setContentText("Won't be able to recover this file!")
        .setConfirmText("Yes,delete it!")
        .show();

A success message：

    new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
        .setTitleText("Good job!")
        .setContentText("You clicked the button!")
        .show();

A message with a custom icon：

    new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
        .setTitleText("Sweet!")
        .setContentText("Here's a custom image.")
        .setCustomImage(R.drawable.custom_img)
        .show();

A message with a custom view：

    final EditText editText = new EditText(this);
    new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
            .setTitleText("Custom view")
            .setConfirmText("Ok")
            .setCustomView(editText)
            .show();


Different ways to bind the listener to button：

    new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
        .setTitleText("Are you sure?")
        .setContentText("Won't be able to recover this file!")
        .setConfirmText("Yes,delete it!")
        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.dismissWithAnimation();
            }
        })
        .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.dismissWithAnimation();
            }
        })
        .show();


Disable button

    final SweetAlertDialog disabledBtnDialog = new SweetAlertDialog(this, SweetAlertDialog.NORMAL_TYPE)
            .setTitleText("Title")
            .setContentText("Disabled button dialog")
            .setConfirmText("Confirm")
            .setCancelText("Cancel")

    disabledBtnDialog.setOnShowListener(new DialogInterface.OnShowListener() {
        @Override
        public void onShow(DialogInterface dialog) {
            disabledBtnDialog.getButton(SweetAlertDialog.BUTTON_CONFIRM).setEnabled(false);
        }
    });
    disabledBtnDialog.show();


**Change** the dialog style upon confirming：

    new SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
        .setTitleText("Are you sure?")
        .setContentText("Won't be able to recover this file!")
        .setConfirmText("Yes,delete it!")
        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog
                    .setTitleText("Deleted!")
                    .setContentText("Your imaginary file has been deleted!")
                    .setConfirmText("OK")
                    .setConfirmClickListener(null)
                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
            }
        })
        .show();

[more android tech shares: pedant.cn](http://www.pedant.cn)

## License

    The MIT License (MIT)

    Copyright (c) 2014 Pedant(http://pedant.cn)

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.


