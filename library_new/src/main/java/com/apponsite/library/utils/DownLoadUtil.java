package com.apponsite.library.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by zhang on 2016/3/24.
 */

public  class DownLoadUtil {

    private static DownLoadTask downLoadTask;
    private static Context mcontext;

    public static DownLoadTask getInstance(Context context) {

        mcontext = context;
//        if (downLoadTask == null) {
            downLoadTask = new DownLoadTask();
//        }
        return downLoadTask;
    }

    public static class DownLoadTask extends AsyncTask<String, Integer, File> {

        private ProgressDialog myDialog;

        @Override
        protected void onPreExecute() {
            myDialog = new ProgressDialog(mcontext);
            myDialog.setMessage("正在下载……");
            myDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            myDialog.setProgress(100);
            myDialog.show();

        }

        @Override
        protected File doInBackground(String... params) {

            OkHttpClient okhttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(params[0]).build();
//            final String fileName = params[0].substring(params[0].lastIndexOf("/") + 1);// 从服务器取文件名称

            final String filePath = params[1];
            final String fileName = params[2];

            Response response;
            File result = null;
            try {
                response = okhttpClient.newCall(request).execute();
                if (response.isSuccessful()) {

                    File dir = new File(filePath);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    InputStream is;
                    FileOutputStream fos;
                    is = response.body().byteStream();
                    long file_length = response.body().contentLength();

                    if (file_length <=0){
                        return null;
                    }
                    result = new File(dir, fileName);

                    int total_length = 0;
                    int len;
                    byte[] buf = new byte[1024];


                    fos = new FileOutputStream(result);
                    while ((len = is.read(buf)) != -1) {
                        total_length += len;
                        int progress_value = (int) ((total_length / (float) file_length) * 100);
                        fos.write(buf, 0, len);
                        publishProgress(progress_value);

                    }
                    fos.flush();

                    try {
                        is.close();
                    } catch (IOException ignored) {
                    }
                    try {
                        fos.close();
                    } catch (IOException ignored) {
                    }
                }

                }catch(FileNotFoundException e1){
                    e1.printStackTrace();
                }catch(IOException e1){
                    e1.printStackTrace();
                }
                return result;

        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            myDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(File result) {

            if (result != null) {
                myDialog.dismiss();

                VersionUtil.setIntent(mcontext, result);

            }else{
                ToastUtils.showToast(mcontext,"暂时没有数据");
                myDialog.dismiss();
            }
        }

    }
}

