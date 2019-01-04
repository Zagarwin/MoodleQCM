package sample;

public class Answer {

    public Answer(double fraction, String text, String t_format, String feedback, String f_format) {
        this.fraction = fraction;
        this.text = text;
        this.t_format = t_format;
        this.feedback = feedback;
        this.f_format = f_format;
    }

    public double getFraction() {
        return fraction;
    }

    public String getText() {
        return text;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getTextFormat() {
        return t_format;
    }

    public String getFeedbackFormat() {
        return f_format;
    }

    private double fraction;
    private String text, feedback;
    private String t_format, f_format;
}
