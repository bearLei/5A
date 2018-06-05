package unit.login;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.puti.education.R;
import com.puti.education.base.PutiActivity;
import com.puti.education.util.ImgLoadUtil;

import butterknife.BindView;
import butterknife.OnFocusChange;
import unit.entity.VerifyInfo;

/**
 * Created by ${lei} on 2018/6/2.
 * 登录页面
 */
public class LoginActivity extends PutiActivity implements LoginView, View.OnClickListener {
    @BindView(R.id.location_desc)
    TextView TLocationDesc;//位置信息
    @BindView(R.id.desc)
    TextView TDesc;//文案描述
    @BindView(R.id.edit_account)
    EditText TEditAccount;//账号
    @BindView(R.id.edit_psw)
    EditText TEditPsw;//密码
    @BindView(R.id.edit_verify)
    EditText TEditVerify;//验证码
    @BindView(R.id.verify)
    ImageView IVerify;//验证码图片
    @BindView(R.id.verify_layout)
    LinearLayout VerifyLayout;
    @BindView(R.id.login)
    TextView TLogin;//登录按钮


    private LoginPtr mPtr;

    @Override
    public int getContentView() {
        return R.layout.puti_login;
    }

    @Override
    public void BindPtr() {
        if (mPtr == null){
            mPtr = new LoginPtr(this,this);
        }
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
        TLogin.setOnClickListener(this);
    }

    @Override
    public void Star() {
        mPtr.star();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPtr.stop();
    }

    /****************************UI*************************/
    @Override
    public void setLocationDesc(String desc) {
        TLocationDesc.setText(desc);
    }

    //展示验证码
    @Override
    public void showVerify(boolean show, VerifyInfo verifyInfo) {
        if (show){
            VerifyLayout.setVisibility(View.VISIBLE);
            if (verifyInfo == null){
                return;
            }
            ImgLoadUtil.displayPic(0, verifyInfo.getVerifyPic(), IVerify);
        }else {
            VerifyLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public String getEditPsw() {
        return TEditPsw.getText().toString();
    }

    @Override
    public String getVerify() {
        return TEditVerify.getText().toString();
    }

    @Override
    public String getEditAccount() {
        return TEditAccount.getText().toString();
    }

    @OnFocusChange({R.id.edit_account,R.id.edit_psw,R.id.edit_verify})
    public void onFocusChange(View view,boolean hasFoucus){
        if (!isFinishing() && hasFoucus){
            view.setBackground(getResources().getDrawable(R.drawable.puti_login_edit_selected_bg));
        }else {
            view.setBackground(getResources().getDrawable(R.drawable.puti_login_edit_unselected_bg));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case R.id.login:
                mPtr.login();
                break;
        }
    }
}
