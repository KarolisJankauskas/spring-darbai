// import {useNavigate} from "react-router";

// export const AuthGuard = (props) => {
//     const { children } = props;
//     const navigate = useNavigate()

//     if (!localStorage.getItem("user")) {
//         navigate("/login")
//     }

//     return <>{children}</>;
// }

export const AuthGuard = (props) => {
    const { children } = props;

    const user = localStorage.getItem("user");
    console.log("User is authenticated:", !!user);

    return <>{children}</>;
};