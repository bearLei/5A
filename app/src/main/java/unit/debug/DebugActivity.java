package unit.debug;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.TextView;

import com.formax.lib_zxing.activity.CodeUtils;
import com.puti.education.R;
import com.puti.education.base.PutiActivity;
import com.puti.education.util.FileUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import unit.moudle.eventregist.PutiChooseStuActivity;

/**
 * Created by lei on 2018/6/14.
 */

public class DebugActivity extends PutiActivity {
    @BindView(R.id.test_choose_stu)
    TextView testChooseStu;
    @BindView(R.id.creat_code)
    TextView creatCode;

    @Override
    public int getContentView() {
        return R.layout.puti_debug_activity;
    }

    @Override
    public void BindPtr() {

    }

    @Override
    public void ParseIntent() {

    }

    @Override
    public void AttachPtrView() {

    }

    @Override
    public void DettachPtrView() {

    }

    @Override
    public void InitView() {

    }

    @Override
    public void Star() {

    }


    @OnClick(R.id.test_choose_stu)
    public void onClick() {
        startActivity(new Intent(this, PutiChooseStuActivity.class));
    }

    @OnClick(R.id.creat_code)
    public void onCreateCode() {
        Bitmap image = CodeUtils.createImage("https://com.tencent.android.qqdownloader/details?id=com.puti.education", 100, 100, null);
        FileUtils.saveBitmap(image, FileUtils.getAppFilePath() + "zxing.jpg", 1);
    }



    @OnClick(R.id.jump)
    public void onJump() {

    }
}
