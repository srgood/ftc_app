package org.firstinspires.ftc.teamcode.opmodes.base;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.control.GamepadWrapper;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

/**
 * Created by Derek on 1/16/2019.
 */

@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class RoverBase extends OpMode {

    //DC Motors
    protected DcMotor
            leftArmExtender,
            rightArmExtender,
            leftArmBase,
            rightArmBase,
            A,
            B,
            C,
            D;

    //Servos
    protected Servo
            leftClaw,
            rightClaw,
            leftClamp,
            rightClamp;

    protected CRServo
            leftPusher,
            rightPusher;

    //Gamepad wrappers
    protected GamepadWrapper
            gamepadWrapper1,
            gamepadWrapper2;


    @Override
    public void init() {
        //error handling initialization code
        boolean error = false;

        try {
            initDCMotors();
        } catch (NullPointerException n) {
            telemetry.addLine("Error initializing DC Motors");
            error = true;
        }

        try {
            initServos();
        } catch (NullPointerException n) {
            telemetry.addLine("Error initializing Servos");
            error = true;
        }

        if(!error) {
            telemetry.addLine("Successfully initialized all hardware");
        }

        try {
            gamepadWrapper1 = new GamepadWrapper(gamepad1);
            gamepadWrapper2 = new GamepadWrapper(gamepad2);
        } catch (RobotCoreException e) {
            e.printStackTrace();
            telemetry.addLine("Error initializing Controllers");
        }

        telemetry.addLine("Base initiation complete");
    }

    @Override
    public void start() {
        telemetry.clearAll();
    }

    @Override
    public void loop() {
        /*
        i'm sad that this unsightly thing has to be here, but for the time being, it has to be.
        The way that the library handles controllers doesn't allow you to easily use the built in callbacks,
        so this is the way it has to be :(
         */
        gamepadWrapper1.update();
        gamepadWrapper2.update();
    }

    /*
        Private utility methods
     */

    //initalizes DC motors
    private void initDCMotors() {
        leftArmExtender  = hardwareMap.dcMotor.get("extLeft");
        rightArmExtender = hardwareMap.dcMotor.get("extRight");
        leftArmBase      = hardwareMap.dcMotor.get("armLeft");
        rightArmBase     = hardwareMap.dcMotor.get("armRight");

        leftArmExtender .setZeroPowerBehavior(BRAKE);
        rightArmExtender.setZeroPowerBehavior(BRAKE);
        rightArmExtender.setDirection(DcMotorSimple.Direction.REVERSE);

        leftArmBase .setZeroPowerBehavior(BRAKE);
        rightArmBase.setZeroPowerBehavior(BRAKE);

        rightArmBase.setDirection(DcMotorSimple.Direction.REVERSE);


        A = hardwareMap.dcMotor.get("backLeft");
        B = hardwareMap.dcMotor.get("frontLeft");
        C = hardwareMap.dcMotor.get("frontRight");
        D = hardwareMap.dcMotor.get("backRight");

        C.setDirection(DcMotorSimple.Direction.REVERSE);
        D.setDirection(DcMotorSimple.Direction.REVERSE);

        resetEncoders();

        A.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        B.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        C.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        D.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        leftArmBase .setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightArmBase.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    //Resets encoders
    private void resetEncoders() {
        A.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        B.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        C.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        D.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftArmBase .setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightArmBase.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //wait for encoders to calibrate
        while (A.isBusy() || B.isBusy() || C.isBusy() || D.isBusy() || leftArmBase.isBusy() || rightArmBase.isBusy()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                telemetry.addData("exception while waiting for encoders",e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
    }

    //initalizes servos
    private void initServos() {
        //claws are the grippers on the main arm of the robot
        leftClaw  = hardwareMap.servo.get("clawLeft");
        rightClaw = hardwareMap.servo.get("clawRight");

        leftClaw.setDirection(Servo.Direction.REVERSE);

        //clamps are the servos that hold the arms in place
        leftClamp  = hardwareMap.servo.get("leftClamp");
        rightClamp = hardwareMap.servo.get("rightClamp");

        rightClamp.setDirection(Servo.Direction.REVERSE);

        rightPusher = hardwareMap.crservo.get("rightPusher");
        leftPusher  = hardwareMap.crservo.get("leftPusher");

        leftPusher.setDirection(CRServo.Direction.REVERSE);
    }
}
