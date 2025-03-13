import {toast, ToastContentProps} from "react-toastify";

type ToastNotificationProps = ToastContentProps<{
  title: string;
  content: string;
}>;

function ToastNotification({
                             data,
                             toastProps,
                           }: ToastNotificationProps) {
  const isColored = toastProps.theme === 'colored';
  return (
      <div className="flex flex-col w-full">
        <h3
            className={`text-sm font-semibold ${isColored ? "text-white" : 'text-zinc-800'}`}
        >
          {data.title}
        </h3>
        <div className="flex items-center justify-between">
          <p className="text-sm">{data.content}</p>
        </div>
      </div>
  );
}

export default function showError(e: unknown) {
  let detail = 'Unknown Error'
  if (e instanceof Error) detail = e.message
  toast.error(ToastNotification, {
    data: {
      title: 'Error!',
      content: detail,
    },
    ariaLabel: 'error',
    autoClose: false,
    icon: false,
    theme: "colored"
  });
}
