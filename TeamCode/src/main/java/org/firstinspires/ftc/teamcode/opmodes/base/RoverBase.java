package org.firstinspires.ftc.teamcode.opmodes.base;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;
import static com.qualcomm.robotcore.hardware.DcMotorSimple.Direction.REVERSE;

/**
 * Created by Derek on 1/16/2019.
 */

public class RoverBase extends OpMode {

    public DcMotor leftArmExtender;
    public DcMotor rightArmExtender;
    public DcMotor leftArmBase;
    public DcMotor rightArmBase;
    public DcMotor A;
    public DcMotor B;
    public DcMotor C;
    public DcMotor D;

    @Override
    public void init() {
        leftArmExtender  = hardwareMap.dcMotor.get("extLeft");
        rightArmExtender = hardwareMap.dcMotor.get("extRight");
        leftArmBase      = hardwareMap.dcMotor.get("armLeft");
        rightArmBase     = hardwareMap.dcMotor.get("armRight");

        leftArmExtender.setZeroPowerBehavior(BRAKE);
        rightArmExtender.setZeroPowerBehavior(BRAKE);
        rightArmExtender.setDirection(REVERSE);

        leftArmBase.setZeroPowerBehavior(BRAKE);
        rightArmBase.setZeroPowerBehavior(BRAKE);
        rightArmBase.setDirection(REVERSE);


        B = hardwareMap.dcMotor.get("frontLeft");
        C = hardwareMap.dcMotor.get("frontRight");
        A = hardwareMap.dcMotor.get("backLeft");
        D = hardwareMap.dcMotor.get("backRight");



        A.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        B.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        C.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        D.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftArmBase.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightArmBase.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        while(A.isBusy() || B.isBusy() || C.isBusy() || D.isBusy() || leftArmBase.isBusy() || rightArmBase.isBusy()) {

        }

        A.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        B.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        C.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        D.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftArmBase.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightArmBase.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



        C.setDirection(REVERSE);
        D.setDirection(REVERSE);
    }

    @Override
    public void loop() {

    }
}
