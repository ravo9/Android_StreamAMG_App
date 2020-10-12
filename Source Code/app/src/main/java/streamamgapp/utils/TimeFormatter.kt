package streamamgapp.utils

class TimeFormatter {

    fun formatTimeToDisplay(timeInSeconds: Int?): String {
        if (timeInSeconds != null) {
            val hours = timeInSeconds / 3600
            val minutes = (timeInSeconds % 3600) / 60
            val seconds = timeInSeconds % 60
            return String.format("%01d:%02d:%02d", hours, minutes, seconds)
        } else {
            return "0:00:00"
        }
    }
}