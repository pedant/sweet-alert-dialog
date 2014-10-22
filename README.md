## Demo运行示意图
![image](https://github.com/pedant/sweet-alert-dialog/raw/master/tmp_screen.jpg)

## 如何开始
只显示标题：

	new SweetAlertDialog(this)
        .setTitleText("Here's a message!")
        .show();

显示标题和内容：

	new SweetAlertDialog(this)
        .setTitleText("Here's a message!")
        .setContentText("It's pretty, isn't it?")
        .show();

显示异常样式：

    new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE)
        .setTitleText("Oops...")
        .setContentText("Something went wrong!")
        .show();

显示成功完成样式：

    new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
        .setTitleText("Good job!")
        .setContentText("You clicked the button!")
        .show();

自定义头部图像：

    new SweetAlertDialog(this, SweetAlertDialog.CUSTOM_IMAGE_TYPE)
        .setTitleText("Sweet!")
        .setContentText("Here's a custom image.")
        .setCustomImage(R.drawable.custom_img)
        .show();